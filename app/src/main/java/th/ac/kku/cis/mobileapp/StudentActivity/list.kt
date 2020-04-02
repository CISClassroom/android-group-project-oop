package th.ac.kku.cis.mobileapp.StudentActivity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import com.squareup.picasso.Picasso

class list : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var newlog: Boolean = false
    lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        if (supportActionBar != null) // เอาแถบบนออก
            supportActionBar?.hide()

        val NameSetting: TextView = findViewById(R.id.name)
        val Profile: ImageView = findViewById(R.id.image)
        val Email: TextView = findViewById(R.id.mail)
        auth = FirebaseAuth.getInstance()
        auth.currentUser!!.email
        val xx: Uri? = auth.currentUser!!.photoUrl
        NameSetting.text = auth.currentUser!!.displayName.toString()
        Picasso.get().load(xx).into(Profile)
        Email.text = auth.currentUser!!.email

        //ปุ่ม logout
        val btlogout: FloatingActionButton = findViewById(R.id.floatingActionButton)
        btlogout.setOnClickListener({ v -> singOut() })

        //เชื่อมหน้า Add_Activity เพิ่มกิจกรรม
        val goActivity: Button = findViewById(R.id.buttonA)
        goActivity.setOnClickListener {

            var i = Intent(this, Add_Activity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        }

        //เชื่อมหน้า All_Activity แสดงกิจกรรม
        val goAll: Button = findViewById(R.id.buttonAll)
        goAll.setOnClickListener {

            var i = Intent(this, All_Activity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        }

    }
    //หังก์ชัน logout
    private fun passproject() {
        if (newlog) {
            var i = Intent(this, MainActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        }

    }
    private fun singOut() {
        auth.signOut()
        newlog = true
        passproject()
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {
            //show.text = "No User"
        } else {
            //show.text = user.email.toString()
            passproject()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuth(account!!)
                //FirebaseAuth(account)
            } catch (e: ApiException) {
                Log.i("Error OOP", e.toString())
                newlog = false
                updateUI(null)
            }
        }
    }

    private fun firebaseAuth(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    newlog = true
                    updateUI(user)
                } else {
                    newlog = false
                    updateUI(null)
                }
            }
    }
}

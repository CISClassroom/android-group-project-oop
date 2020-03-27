package th.ac.kku.cis.mobileapp.StudentActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var auth: FirebaseAuth
    lateinit var googleClient: GoogleSignInClient
    var newpropro:Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportActionBar != null) // เอาแถบบนออก
            supportActionBar?.hide()


        login.setOnClickListener( {v->singIn()} )
        // sigout.setOnClickListener({v->singOut()})

        var gso = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        )
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleClient = GoogleSignIn.getClient(this, gso)
        auth = FirebaseAuth.getInstance()


    }


    private fun newproject() {
        if(newpropro) {
            var i = Intent(this, list::class.java)//ลิงค์ไปหน้า activity_home

            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)

        }
    }

    private fun singOut() {
        auth.signOut()
        googleClient.signOut().addOnCompleteListener(this) {
            updateUI(null)
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {

        } else {

            newproject()
        }

    }

    private fun singIn() {


        if(newpropro){
            newproject()
        }
        else {
            var signInInent = googleClient.signInIntent
            startActivityForResult(signInInent, 101)
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
                Log.i("Error OOP",e.toString())
                newpropro = false
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
                    newpropro = true
                    updateUI(user)
                } else {
                    newpropro = false
                    updateUI(null)
                }
            }
    }

}


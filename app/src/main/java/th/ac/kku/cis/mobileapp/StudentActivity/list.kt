package th.ac.kku.cis.mobileapp.StudentActivity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class list : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
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


        //เชื่อมหน้า
        val goActivity: Button = findViewById(R.id.buttonA)

        goActivity.setOnClickListener {

            var i = Intent(this, Add_Activity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        }


        val goAll: Button = findViewById(R.id.buttonAll)

        goAll.setOnClickListener {

            var i = Intent(this, All_Activity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        }

    }
}

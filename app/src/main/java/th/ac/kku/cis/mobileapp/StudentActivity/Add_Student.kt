package th.ac.kku.cis.mobileapp.StudentActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Add_Student : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__student)

        if (supportActionBar != null) // เอาแถบบนออก
            supportActionBar?.hide()
    }
}

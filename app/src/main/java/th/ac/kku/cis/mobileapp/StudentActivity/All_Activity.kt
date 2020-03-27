package th.ac.kku.cis.mobileapp.StudentActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class All_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_)

        if (supportActionBar != null) // เอาแถบบนออก
            supportActionBar?.hide()
    }
}

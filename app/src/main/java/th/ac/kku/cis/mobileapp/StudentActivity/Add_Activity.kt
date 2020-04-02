package th.ac.kku.cis.mobileapp.StudentActivity

import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add_.*


//add กิจกรรม
class Add_Activity : AppCompatActivity() {


    lateinit var activityList: MutableList<Activity>
    lateinit var mDB: DatabaseReference
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_)

        if (supportActionBar != null) // เอาแถบบนออก
            supportActionBar?.hide()

        activityList = mutableListOf()
        //2
        auth = FirebaseAuth.getInstance()
        // Id = auth.currentUser!!.uid
        mDB = FirebaseDatabase.getInstance().reference
        //ปุ่มbutton ok_activity กดบันทึก
        ok_activity.setOnClickListener {
            AddData("String")
        }

    }
//บันทึกเข้า database
    fun AddData(data: String) {
        var newData: Activity = Activity.create()
        val obj = mDB.child("Activity").push()
        newData.AcID = ac_numm.text.toString()
        newData.NameActivity = add_activity.text.toString()
        newData.UnitActivity = ac_num.text.toString()
        newData.ActivityId = obj.key
        obj.setValue(newData)
        ac_numm

        Toast.makeText(applicationContext,"Activity save successfully",Toast.LENGTH_LONG).show()
        finish()//กลับไปหน้าก่อนนี้
    }




}

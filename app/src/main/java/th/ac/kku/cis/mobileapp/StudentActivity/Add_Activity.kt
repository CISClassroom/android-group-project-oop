package th.ac.kku.cis.mobileapp.StudentActivity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add_.*

class Activity{
    companion object Factory {
        fun create(): Activity = Activity()
    }

    var ActivityId: String? = null
    var NameActivity: String? = null


}

class Add_Activity : AppCompatActivity() {

//    lateinit var add_activity: TextInputEditText
//    lateinit var ok_activity: Button

    lateinit var listview:ListView
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
        ok_activity.setOnClickListener {
            AddData("String")
        }

//
//        listView= findViewById(R.id.listview)
//        mDB.addChildEventListener(object : ValueEventListener, ChildEventListener {
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onChildRemoved(p0: DataSnapshot) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//                if (p0!!.exists()){
//                    for (h in p0.children){
//                        val activity = h.getValue(Activity::class.java)
//                        activityList.add(activity!!)
//
//                    }
//
//                    val adapter = ActiityAapter(applicationContext,R.layout.activities,activityList)
//                    listView.adapter = adapter
//                }
//            }
//
//        })
    }
    fun AddData(data: String) {
        var newData: Activity = Activity.create()
        val obj = mDB.child("note").push()
        newData.NameActivity = add_activity.text.toString()

        newData.ActivityId = obj.key
        obj.setValue(newData)


        Toast.makeText(applicationContext,"Activity save successfully",Toast.LENGTH_LONG).show()
    }




}

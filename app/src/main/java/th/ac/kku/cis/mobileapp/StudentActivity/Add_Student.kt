package th.ac.kku.cis.mobileapp.StudentActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add__student.*

class Add_Student : AppCompatActivity() {
    lateinit var studentList: MutableList<Student>
    lateinit var mDB: DatabaseReference
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__student)

        if (supportActionBar != null) // เอาแถบบนออก
            supportActionBar?.hide()
        studentList = mutableListOf()
        //2 เพิ่มนักศึกษา--------------
        auth = FirebaseAuth.getInstance()
        // Id = auth.currentUser!!.uid
        mDB = FirebaseDatabase.getInstance().reference
        ok_student.setOnClickListener {
            AddDataStudent("String")
        }

    }
    fun AddDataStudent(data: String) {

        var name = getIntent().getStringExtra("name1")
        var newData: Student = Student.create()
        val obj = mDB.child("Student").push()
        newData.NewName = name.toString()
        newData.NameStudent = add_student.text.toString()
        newData.IdStudent = add_id.text.toString()
        newData.Id = obj.key
        obj.setValue(newData)


        Toast.makeText(applicationContext,"Student save successfully", Toast.LENGTH_LONG).show()
        var i = Intent(this, showstudent::class.java)
        i.putExtra("name1",name)//ส่งไป showstudent
        finish()//กลับไปหน้าก่อนนี้
    }
    //------------
}

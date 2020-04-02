package th.ac.kku.cis.mobileapp.StudentActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add__student.*
//add นักศึกษา
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
        auth = FirebaseAuth.getInstance()
        // Id = auth.currentUser!!.uid
        mDB = FirebaseDatabase.getInstance().reference
        //ปุ่มbutton ok_student กดบันทึก
        ok_student.setOnClickListener {
            AddDataStudent("String")
        }

    }
    //เพิ่มนักศึกษาใน database
    fun AddDataStudent(data: String) {

        var name = getIntent().getStringExtra("name1")//รับค่าจาก Shoestudent หน้าแสดงนักศึกษา
        var newData: Student = Student.create()
        val obj = mDB.child("Student").push()
        newData.NewName = name.toString()
        newData.NameStudent = add_student.text.toString()
        newData.IdStudent = add_id.text.toString()
        newData.Id = obj.key
        obj.setValue(newData)


        Toast.makeText(applicationContext,"Student save successfully", Toast.LENGTH_LONG).show()
        var i = Intent(this, showstudent::class.java)
        i.putExtra("name1",name)//ส่งค่ากลับไป showstudent
        startActivity(i)//รีเฟรชกลับไปหน้าก่อนนี้เพื่อรีเฟรชfirebase มาแสดง
    }
    //------------
}

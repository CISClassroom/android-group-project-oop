package th.ac.kku.cis.mobileapp.StudentActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class showstudent : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference
    lateinit var adapter: ToDoStudentAdapter
    private var listViewItems: ListView? = null
    var toDoStudentList: MutableList<Student>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showstudent)
        if (supportActionBar != null) // เอาแถบบนออก
            supportActionBar?.hide()
        //เชื่อมหน้า
        val goActivity: Button = findViewById(R.id.button)

        goActivity.setOnClickListener {
            var i = Intent(this, Add_Student::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)

            //แสดง นักศึกษา
            listViewItems = findViewById<View>(R.id.listview) as ListView
            toDoStudentList = mutableListOf<Student>()
            adapter = ToDoStudentAdapter(this, toDoStudentList!!)
            listViewItems!!.setAdapter(adapter)

            mDatabase = FirebaseDatabase.getInstance().reference
            mDatabase.child("Student").addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val items = dataSnapshot.children.iterator()
                    // Check if current database contains any collection
                    if (items.hasNext()) {
                        while (items.hasNext()) {
                            val toDoListindex = items.next()
                            val map = toDoListindex.getValue() as HashMap<String, Any>

                            // add data to object
                                val todoItem = Student.create()
                                todoItem.NameStudent = map.get("nameStudent") as String?
                                todoItem.IdStudent = map.get("idStudent") as String?
                                toDoStudentList!!.add(todoItem);
                                adapter.notifyDataSetChanged()

                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                }
            })

        }
    }
}

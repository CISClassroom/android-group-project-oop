package th.ac.kku.cis.mobileapp.StudentActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import th.ac.kku.cis.mobileapp.StudentActivity.ToDoItemAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_all_.*
import kotlinx.android.synthetic.main.activity_all_.listview
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_showstudent.*

class All_Activity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference
    lateinit var adapter: ToDoItemAdapter
    private var listViewItems: ListView? = null
    var toDoItemList: MutableList<Activity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_)



            //แสดง กิจกรรม
            listViewItems = findViewById<View>(R.id.listview) as ListView
            toDoItemList = mutableListOf<Activity>()
            adapter = ToDoItemAdapter(this, toDoItemList!!)
            listViewItems!!.setAdapter(adapter)

            mDatabase = FirebaseDatabase.getInstance().reference
            mDatabase.child("Activity").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val items = dataSnapshot.children.iterator()
                    // Check if current database contains any collection
                    if (items.hasNext()) {
                        while (items.hasNext()) {
                            val toDoListindex = items.next()
                            val map = toDoListindex.getValue() as HashMap<String, Any>
                            // add data to object
                            val todoItem = Activity.create()
                            todoItem.NameActivity = map.get("nameActivity") as String?
                            todoItem.UnitActivity = map.get("unitActivity") as String?
                            toDoItemList!!.add(todoItem);
                            adapter.notifyDataSetChanged()
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                }
            })

        //กด listview ไปหน้า showstudent  แสดงนักศึกษา
//        listview.setOnItemClickListener { parent, view, position, id ->
//            var i = Intent(this, showstudent::class.java)
//            i.putExtra("i", position)
//            startActivity(i)
//        }

        listview.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as Activity
            //Toast.makeText(this,selectedItem,Toast.LENGTH_SHORT).show()
            val intent = Intent(this, showstudent::class.java)

            intent.putExtra("name1", selectedItem.NameActivity)//ส่งไปยัง showstudent

            startActivity(intent)
        }

        }
    }


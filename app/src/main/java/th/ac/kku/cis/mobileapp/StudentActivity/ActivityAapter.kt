package th.ac.kku.cis.mobileapp.StudentActivity

import android.content.Context
import android.os.Build
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi

class ToDoItemAdapter(context: android.content.Context, toDoItemList: MutableList<Activity>) : BaseAdapter() {

        private val mInflater: LayoutInflater = LayoutInflater.from(context)
        private var itemList = toDoItemList


        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                // create object from view
                val AcID: String = itemList.get(position).AcID as String
                val NameActivity: String = itemList.get(position).NameActivity as String
                val UnitActivity: String = itemList.get(position).UnitActivity as String
                val view: View
                val vh: ListRowHolder

                // get list view
                if (convertView == null) {
                        view = mInflater.inflate(R.layout.list_activity, parent, false)
                        vh = ListRowHolder(view)
                        view.tag = vh
                } else {
                        view = convertView
                        vh = view.tag as ListRowHolder
                }

                // add text to view
                vh.label4.text = AcID
                vh.label2.text = NameActivity
                vh.label3.text = UnitActivity

                return view
        }

        override fun getItem(index: Int): Any {
                return itemList.get(index)
        }

        override fun getItemId(index: Int): Long {
                return index.toLong()
        }

        override fun getCount(): Int {
                return itemList.size
        }

        private class ListRowHolder(row: View?) {
                val label4: TextView = row!!.findViewById<TextView>(R.id.textView7) as TextView
                val label2: TextView = row!!.findViewById<TextView>(R.id.textView2) as TextView
                val label3: TextView = row!!.findViewById<TextView>(R.id.textView6) as TextView
        }
}

class ToDoStudentAdapter (context: android.content.Context, toDoStudentList: MutableList<Student>) : BaseAdapter() {

        private val mInflater: LayoutInflater = LayoutInflater.from(context)
        private var itemList = toDoStudentList


        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

                val Newname: String = itemList.get(position).NewName as String
                val name: String = itemList.get(position).NameStudent as String
                val id: String = itemList.get(position).IdStudent as String
                val view: View
                val vh: ListRowHolder

                // get list view
                if (convertView == null) {
                        view = mInflater.inflate(R.layout.list_student, parent, false)
                        vh = ListRowHolder(view)
                        view.tag = vh
                } else {
                        view = convertView
                        vh = view.tag as ListRowHolder
                }

                // add text to view
                vh.label2.text = name
                vh.label3.text = id
                vh.label4.text = Newname

                return view
        }

        override fun getItem(index: Int): Any {
                return itemList.get(index)
        }

        override fun getItemId(index: Int): Long {
                return index.toLong()
        }

        override fun getCount(): Int {
                return itemList.size
        }

        private class ListRowHolder(row: View?) {
                val label2: TextView = row!!.findViewById<TextView>(R.id.textView8) as TextView
                val label3: TextView = row!!.findViewById<TextView>(R.id.textView9) as TextView
                val label4: TextView = row!!.findViewById<TextView>(R.id.textView11) as TextView
        }
}
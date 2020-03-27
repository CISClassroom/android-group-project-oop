package th.ac.kku.cis.mobileapp.StudentActivity

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ActiityAapter(val mCtx: Context,val layoutResId:Int,val activityList:List<Activity>)
    :ArrayAdapter<Activity>(mCtx,layoutResId,activityList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
       val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx);
        val view:View = layoutInflater.inflate(layoutResId,null)

        val textViewName =view.findViewById<TextView>(R.id.textViewName);

        val activity = activityList[position]

        textViewName.text = activity.NameActivity

        return view;
    }
}
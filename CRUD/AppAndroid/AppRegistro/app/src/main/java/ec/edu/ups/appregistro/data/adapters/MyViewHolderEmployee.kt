package ec.edu.ups.appregistro.data.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import ec.edu.ups.appregistro.R
import ec.edu.ups.appregistro.data.models.Employee
//import ec.edu.ups.appregistro.databinding.RowEmployeesBinding
import ec.edu.ups.appregistro.ui.employee.EditEmployoeeActivity
import ec.edu.ups.appregistro.util.MyMessages

//import kotlinx.android.synthetic.main.row_teacher.view.*


//https://github.com/seoleo96/SimpleListWithKotlin/blob/master/app/src/main/java/com/seoleo96/retrolist/adapter/ViewHolder.kt
open class MyViewHolderEmployee(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var name: TextView
    lateinit var email: TextView
    lateinit var prefix: TextView
    fun bind(employee: Employee) {
        prefix = itemView.findViewById(R.id.prefix)
        name = itemView.findViewById(R.id.name)
        email = itemView.findViewById(R.id.email)

        name.text = employee?.name
        prefix.text = employee.id.toString()
        email.text = employee?.email


        itemView.setOnClickListener {
            //Thread.sleep(1000)
            //Toast.makeText(itemView.context, employee?.name.toString(), Toast.LENGTH_SHORT).show()
            MyMessages.toast(itemView.context, employee?.name.toString())
            val intent = Intent(itemView.context, EditEmployoeeActivity::class.java)
            intent.putExtra("per_id", name.id)
            //intent.putExtra("img", teacherlist?.img)
            itemView.context.startActivity(intent)
        /*val intent = Intent(itemView.context, Main2Activity::class.java)
            intent.putExtra("name", teacherlist?.name)
            intent.putExtra("img", teacherlist?.img)
            itemView.context.startActivity(intent)*/
        }
    }

    /*
    var name: TextView
    var email: TextView
    var prefix: TextView

    init {
        prefix = itemLayoutView.findViewById(R.id.prefix)
        name = itemLayoutView.findViewById(R.id.name)
        email = itemLayoutView.findViewById(R.id.email)

        itemLayoutView.setOnClickListener {
            //Thread.sleep(1000)
            Log.i("Prueba",name.toString())
            //Toast.makeText(itemView.context, name.toString(), Toast.LENGTH_SHORT).show()
            val intent = Intent(itemView.context, EditEmployoeeActivity::class.java)
            intent.putExtra("per_id", name.text)
            //intent.putExtra("img", teacherlist?.img)
            itemView.context.startActivity(intent)
        }

    }*/






}

/*
open class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
{
    fun bind(teacherlist: Teacher) {
        itemView.textView.text = teacherlist?.name
        Glide.with(itemView.context).asBitmap().load(teacherlist?.img).into(itemView.circleImageView)

        itemView.setOnClickListener {
            Thread.sleep(1000)
            Toast.makeText(itemView.context, teacherlist?.name.toString(), Toast.LENGTH_SHORT).show()
            val intent = Intent(itemView.context, Main2Activity::class.java)
            intent.putExtra("name", teacherlist?.name)
            intent.putExtra("img", teacherlist?.img)
            itemView.context.startActivity(intent)
        }
    }
}

open class MyViewHolderEmployee(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
    //TODO: nuevo
    val binding = RowEmployeesBinding.bind(itemLayoutView)
    fun bind(teacherlist: Employee) {
        // itemView.textView.text = teacherlist?.name
        //Glide.with(itemView.context).asBitmap().load(teacherlist?.img).into(itemView.circleImageView)


        itemView.setOnClickListener {
            //TODO: nuevo
            //TODO: https://www.youtube.com/watch?v=yE2Y2q4iWpU
            //TODO: https://cursokotlin.com/capitulo-29-view-binding-en-kotlin/
           // binding.username.text=teacherlist.name


            Thread.sleep(1000)
            Toast.makeText(itemView.context, teacherlist.name.toString(), Toast.LENGTH_SHORT)
                .show()
            val intent = Intent(itemView.context, EditEmployoeeActivity::class.java)
            intent.putExtra("name", teacherlist.name)
            //intent.putExtra("img", teacherlist?.img)
            itemView.context.startActivity(intent)
        }

*/

        /*

         lateinit var username:TextView
    init {
        username = itemLayoutView.findViewById(R.id.username)

    }
         *//*
    }
}*/


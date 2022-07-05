package ec.edu.ups.appregistro.data.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ec.edu.ups.appregistro.data.models.Employee
import ec.edu.ups.appregistro.R
import ec.edu.ups.appregistro.ui.employee.EditEmployoeeActivity

//class RecyclerAdapter(private var employeeList: List<Employee>) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolderEmployee>() {
    class RecyclerAdapter(val employeeList: List<Employee>) : RecyclerView.Adapter<MyViewHolderEmployee>() {

    //var employeeList : List<Employee> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderEmployee {

        //val view = LayoutInflater.from(parent.context).inflate(R.layout.row_users,parent,false)
        //return MyViewHolder(view)
        /*return MyViewHolderEmployee(
            LayoutInflater.from(parent.context).inflate(R.layout.row_employees, parent, false)
        )*/
        val layoutInflater = LayoutInflater.from(parent.context)
        val view  = layoutInflater.inflate(R.layout.row_employees, parent, false)
        val viewHolder = MyViewHolderEmployee(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return employeeList?.size!!
    }

    override fun onBindViewHolder(holder: MyViewHolderEmployee, position: Int) {

        //holder.username.text = employeeList.get(position).username
       //Glide.with(context).load(employeeList.get(position).image)
         //   .apply(RequestOptions().centerCrop())
         //   .into(holder.image)


/*
val dataModel=employeeList.get(position)
        holder.name.text=dataModel.name
        holder.email.text=dataModel.email
        holder.prefix.text=dataModel.id.toString()
*/
        //TODO: nuevo
        //holder.bind(dataModel)
        employeeList?.get(position)?.let { holder.bind(it) }

        //Log.d("Ankan", "Code? ${employeeList.name}")

    }



    /*class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val username: TextView = itemView!!.findViewById(R.id.username)
        //val image: ImageView = itemView!!.findViewById(R.id.image)

    }*/


    /*
    class MyViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {

         var name:TextView
         var email:TextView
         var prefix:TextView

        init {
            prefix = itemLayoutView.findViewById(R.id.prefix)
            name = itemLayoutView.findViewById(R.id.name)
            email = itemLayoutView.findViewById(R.id.email)

            itemLayoutView.setOnClickListener {
                //Thread.sleep(1000)
                Toast.makeText(itemView.context, name.toString(), Toast.LENGTH_SHORT).show()
                val intent = Intent(itemView.context, EditEmployoeeActivity::class.java)
                intent.putExtra("per_id", name.text)
                //intent.putExtra("img", teacherlist?.img)
                itemView.context.startActivity(intent)
            }

        }




    }*/

}
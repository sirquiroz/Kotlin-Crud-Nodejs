package ec.edu.ups.appregistro.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ec.edu.ups.appregistro.data.models.Employee
import ec.edu.ups.appregistro.R

class RecyclerAdapter(private var employeeList: List<Employee>) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    //var employeeList : List<Employee> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        //val view = LayoutInflater.from(parent.context).inflate(R.layout.row_users,parent,false)
        //return MyViewHolder(view)
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_users, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        //holder.username.text = employeeList.get(position).username
       //Glide.with(context).load(employeeList.get(position).image)
         //   .apply(RequestOptions().centerCrop())
         //   .into(holder.image)

        val dataModel=employeeList.get(position)

        holder.username.text=dataModel.name

        Log.d("Ankan", "Code? ${dataModel.name}")

    }

    fun setMovieListItems(employessList: List<Employee>){
        this.employeeList = employessList;
        notifyDataSetChanged()
    }

    /*class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val username: TextView = itemView!!.findViewById(R.id.username)
        //val image: ImageView = itemView!!.findViewById(R.id.image)

    }*/


    class MyViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        lateinit var username:TextView
        init {
            username = itemLayoutView.findViewById(R.id.username)

        }

    }

}
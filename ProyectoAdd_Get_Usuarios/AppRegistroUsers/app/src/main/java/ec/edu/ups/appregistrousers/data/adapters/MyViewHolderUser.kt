package ec.edu.ups.appregistrousers.data.adapters

import android.content.Intent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ec.edu.ups.appregistrousers.R
import ec.edu.ups.appregistrousers.data.models.User
import ec.edu.ups.appregistrousers.ui.UserCreateActivity

open class MyViewHolderUser(itemview: View) : RecyclerView.ViewHolder(itemview) {
    lateinit var name: TextView
    lateinit var lastname: TextView
    fun bind(user: User) {
        name = itemView.findViewById(R.id.nombre)
        lastname = itemView.findViewById(R.id.apellido)

        name.text = user.name
        lastname.text= user.lastname

        itemView.setOnClickListener {
            Toast.makeText(itemView.context, user.name.toString(),Toast.LENGTH_SHORT).show()

            /*val intent = Intent(itemView.context, UserCreateActivity::class.java)
            intent.putExtra("nombre",user.name)
            intent.putExtra("apellido",user.lastname)
            itemView.context.startActivity(intent)*/

        }

    }

}
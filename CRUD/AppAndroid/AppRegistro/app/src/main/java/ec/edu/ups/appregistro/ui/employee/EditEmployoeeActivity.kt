package ec.edu.ups.appregistro.ui.employee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ec.edu.ups.appregistro.R

class EditEmployoeeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_employeee)
        val intent = intent.extras
        val per_id = intent?.get("id")
        //val email = intent?.get("email")
        //val img = intent?.get("img")

       // Glide.with(this).load(img).centerCrop().into(circleImageView)
       // name2.text = name.toString()
    }
}
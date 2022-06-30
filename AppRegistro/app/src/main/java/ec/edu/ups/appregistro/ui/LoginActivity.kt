package ec.edu.ups.appregistro.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import ec.edu.ups.appregistro.R
import ec.edu.ups.appregistro.ui.employee.ListEmployees
import ec.edu.ups.appregistro.util.SharedPrefManager

class Login : AppCompatActivity() {
    private lateinit var txtUser: EditText
    private lateinit var txtPwd: EditText
    private lateinit var postButton: ImageButton

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        if (SharedPrefManager.getInstance(this).isLoggedIn) {
            val intent = Intent(applicationContext, ListEmployees::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
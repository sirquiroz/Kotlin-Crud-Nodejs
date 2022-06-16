package ec.edu.ups.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ec.edu.ups.app.R

class NewRegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_registro)
    }
    fun regresar(view: View){
        this.onBackPressed()
    }
    fun guardarRegistro(view: View){
        //Log.i("Login", "Log de Login")
        Mensajes.alarmas(applicationContext, "Guardar Registro en desarrollo")
        /*val intent = Intent(this, HomeActivity::class.java).apply {
            //putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)*/
    }
}
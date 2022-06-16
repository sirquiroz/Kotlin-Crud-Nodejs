package ec.edu.ups.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.Navigation
import ec.edu.ups.app.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    //usar cardview https://www.youtube.com/watch?v=J2nuk1Q_bNs

    }

    fun menuHome(item: MenuItem){
        when (item.getItemId()) {
            R.id.navigation_new -> {
                Mensajes.alarmas(applicationContext,"Nuevo Registro")
                val intent = Intent(this, NewRegistroActivity::class.java).apply {
                    //putExtra(EXTRA_MESSAGE, message)
                }
                startActivity(intent)
            }
            R.id.navigation_home -> {
                finish();
                //System.runFinalizersOnExit(true)
                //this.onBackPressed();
            }
        }
    }
}
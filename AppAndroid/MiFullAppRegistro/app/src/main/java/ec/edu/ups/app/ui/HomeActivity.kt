package ec.edu.ups.app.ui

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import ec.edu.ups.app.R
import ec.edu.ups.app.config.AuthInterceptor
import ec.edu.ups.app.config.Constants
import ec.edu.ups.app.config.Mensajes
import ec.edu.ups.app.config.SharedPrefManager
import ec.edu.ups.app.data.PersonaResponse
import ec.edu.ups.app.model.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

//https://medium.com/swlh/okhttp-interceptors-with-retrofit-2dcc322cc3f3
class HomeActivity : AppCompatActivity() {
    lateinit var service: ApiService
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()

        if (!SharedPrefManager.getInstance(this).isLoggedIn) {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val sp = getSharedPreferences(Constants.SHARED_PREF_NAME, MODE_PRIVATE)
        sp.edit().putBoolean("isLoggedIn", false).apply()
        logout()
    }
    private fun okhttpClient(context: Context?): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(40, TimeUnit.SECONDS)
            .connectTimeout(40, TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor(context))
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //usar cardview https://www.youtube.com/watch?v=J2nuk1Q_bNs
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient(this))
            .build()
        service = retrofit.create<ApiService>(ApiService::class.java)

    }

    private suspend fun loadInfo() {
        //Recibimos todos los lista_personas
        val aut =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImVtYWlsIjoiYWRtaW4iLCJwd2QiOiIxMjMiLCJpZCI6MSwibmFtZSI6Ik1pZ3VlbCBRdWlyb3oifSwiaWF0IjoxNjU2MzQ1NTY4LCJleHAiOjE2NTYzNDg1Njh9.CJgjiM1AMMLrsg9M37eFcIhXpeBxUWBhdY4xrZPvuAs"
        service.getAllEmployees(aut).enqueue(object : Callback<List<PersonaResponse>> {
            override fun onResponse(
                call: Call<List<PersonaResponse>>?,
                response: Response<List<PersonaResponse>>?
            ) {
                val lista_personas = response?.body()
                Log.i(Constants.TAG_LOGS, Gson().toJson(lista_personas))
            }

            override fun onFailure(call: Call<List<PersonaResponse>>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }

    fun logout() {

        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
    }

    fun menuHome(item: MenuItem) {
        when (item.getItemId()) {
            R.id.navigation_new -> {
                /*Mensajes.toast(applicationContext,"Nuevo Registro")
                val intent = Intent(this, NewRegistroActivity::class.java).apply {
                    //putExtra(EXTRA_MESSAGE, message)
                }
                startActivity(intent)
                */
                CoroutineScope(Dispatchers.IO).launch {
                    loadInfo()
                }


            }
            R.id.navigation_home -> {
                //finish();
                //System.runFinalizersOnExit(true)
                //this.onBackPressed();
                val editor = getSharedPreferences(Constants.SHARED_PREF_NAME, MODE_PRIVATE).edit()
                editor.clear().apply()
                this.onBackPressed();
            }
        }
    }

/*


    fun getAllEmpleados() {
        //Recibimos todos los lista_personas
        service.getAllEmployees().enqueue(object : Callback<List<Persona>> {
            override fun onResponse(
                call: Call<List<Persona>>?,
                response: Response<List<Persona>>?
            ) {
                val lista_personas = response?.body()
                Log.i(Constants.TAG_LOGS, Gson().toJson(lista_personas))
            }

            override fun onFailure(call: Call<List<Persona>>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }

    fun getEmpleadoById() {
        //Recibimos los datos del empleado con ID = 1
        var objPersona: Persona? = null
        service.getEmployeeById(3).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>?, response: Response<Persona>?) {
                objPersona = response?.body()
                Log.i(Constants.TAG_LOGS, Gson().toJson(objPersona))
                Log.i(Constants.TAG_LOGS, objPersona.toString())
            }

            override fun onFailure(call: Call<Persona>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }

    fun editEmpleado() {
        var objPersona: Persona? = Persona(1, "Miguel", "mquir@mail.com", 99)
        //Editamos los datos por POST
        service.editEmployeeById(1, objPersona).enqueue(object : Callback<Persona> {
            override fun onResponse(call: Call<Persona>?, response: Response<Persona>?) {
                objPersona = response?.body()
                Log.i(Constants.TAG_LOGS, Gson().toJson(objPersona))


            }

            override fun onFailure(call: Call<Persona>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }

*/

}


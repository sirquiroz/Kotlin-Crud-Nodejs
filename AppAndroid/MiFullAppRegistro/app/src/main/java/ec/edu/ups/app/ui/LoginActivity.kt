package ec.edu.ups.app.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import ec.edu.ups.app.R
import ec.edu.ups.app.config.AuthInterceptor
import ec.edu.ups.app.config.Constants
import ec.edu.ups.app.config.Mensajes
import ec.edu.ups.app.config.SharedPrefManager
import ec.edu.ups.app.data.LoginRequest
import ec.edu.ups.app.data.LoginResponse
import ec.edu.ups.app.model.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * @author Miguel Quiroz
 * Clase LoginActivity para el login
 * Trabajar con coroutines: https://camposha.info/android-examples/android-coroutines/#gsc.tab=0
 */
class LoginActivity : AppCompatActivity() {
    lateinit var service: ApiService
    private lateinit var txtUser: EditText
    private lateinit var txtPwd: EditText
    private lateinit var postButton: ImageButton

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        if (SharedPrefManager.getInstance(this).isLoggedIn) {
            val intent = Intent(applicationContext, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
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
        setContentView(R.layout.activity_login)
        postButton = findViewById(R.id.imageButton)
        txtUser = findViewById(R.id.txtUser)
        txtPwd = findViewById(R.id.txtPwd)


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient(this))
            .build()
        service = retrofit.create<ApiService>(ApiService::class.java)







        postButton.setOnClickListener {
            CoroutineScope(IO).launch {
                getlogin()
            }
        }
    }
//https://github.com/ArisGuimera/RetrofitKotlinExample/tree/feature/2022updated/app/src/main/java/com/cursokotlin/retrofitkotlinexample varios codigos de ejemplos
    //https://www.youtube.com/watch?v=L3pM5YuxYp4 para probar los query
    //https://www.youtube.com/watch?v=cL3bXzUBFUA&t=1375s
    //https://github.com/probelalkhan/kotlin-retrofit-tutorial/blob/master/app/src/main/java/simplifiedcoding/net/kotlinretrofittutorial/storage/SharedPrefManager.kt
    //https://github.com/tirgei/RetrofitAuthorization/blob/master/app/src/main/java/com/tirgei/retrofitauthorization/utils/SessionManager.kt

    private suspend fun getlogin() {
        val email = txtUser.text.toString().trim()
        val password = txtPwd.text.toString().trim()
        if (email.isEmpty()) {
            withContext(Main) {
                txtUser.error = "El usuario es requerido"
                txtUser.requestFocus()
            }
            return
        }
        if (password.isEmpty()) {
            withContext(Main) {
                txtPwd.error = "Se requiere la clave"
                txtPwd.requestFocus()
            }
            return
        }

        val obj: LoginRequest = LoginRequest(email.toString(), password.toString())
        service.getLogin(obj).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.code() == 200 && response.body()?.islogged == true && !response.body()?.error!!) {
                    //logThread(response.code().toString())
                    var objRes: LoginResponse? = response?.body()
                    Log.i(Constants.TAG_LOGS, Gson().toJson(objRes))
                    //Log.i(Constants.TAG_LOGS, objPersona.toString())

                    SharedPrefManager.getInstance(this@LoginActivity.applicationContext)
                        .saveUser(response.body()?.user!!)

                    val intent = Intent(applicationContext, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)

                } else {
                    //Toast.makeText(applicationContext, response.body()?.message.toString(), Toast.LENGTH_LONG).show()
                    Mensajes.toast(applicationContext, response.body()?.message.toString())
                }

                /*
                val loginResponse = response.body()

                    if (loginResponse?.statusCode == 200 && loginResponse.user != null) {
                        sessionManager.saveAuthToken(loginResponse.authToken)
                    } else {
                        // Error logging in
                    }
                * */


            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                //t?.printStackTrace()
                //Log.i(Constants.TAG_LOGS, t.toString())
                Mensajes.toast(applicationContext, t.toString())
            }
        })
    }

    private fun logThread(methodName: String) {
        println("debug: ${methodName}: ${Thread.currentThread().name}")
        Mensajes.toast(applicationContext, "debug: ${methodName}: ${Thread.currentThread().name}")
    }
}
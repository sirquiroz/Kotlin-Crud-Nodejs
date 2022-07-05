package ec.edu.ups.appregistro.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.EditText
import android.widget.ImageButton
import com.google.gson.Gson
import ec.edu.ups.appregistro.R

import ec.edu.ups.appregistro.data.requests.LoginRequest
import ec.edu.ups.appregistro.data.responses.LoginResponse
import ec.edu.ups.appregistro.data.services.ApiClient
import ec.edu.ups.appregistro.ui.employee.ListEmployeesActivity

import ec.edu.ups.appregistro.util.Constants
import ec.edu.ups.appregistro.util.MyMessages
import ec.edu.ups.appregistro.util.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//import ec.edu.ups.appregistro.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var txtUser: EditText
    private lateinit var txtPwd: EditText
    private lateinit var postButton: ImageButton
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager
    //TODO: https://www.youtube.com/watch?v=yE2Y2q4iWpU
    //TODO: https://cursokotlin.com/capitulo-29-view-binding-en-kotlin/
    //private lateinit var binding : ActivityLoginBinding


    override fun onDestroy() {
        super.onDestroy()
        // compositeDisposable!!.clear() // RxJava
        // job!!.cancel() // Coroutine
    }

    override fun onStart() {
        super.onStart()
        sessionManager.fetchAuthToken()?.let {
            val intent = Intent(applicationContext, ListEmployeesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        /*  if (SharedPrefManager.getInstance(this).isLoggedIn) {
              val intent = Intent(applicationContext, ListEmployeesActivity::class.java)
              intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
              startActivity(intent)
          }*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //binding = ActivityLoginBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        txtUser = findViewById(R.id.txtUser)
        txtPwd = findViewById(R.id.txtPwd)
        postButton = findViewById(R.id.imageButton)
        //binding.postButton.setOnClickListener {
        postButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                getlogin()
            }
        }
    }

    private suspend fun getlogin() {
        val email = txtUser.text.toString().trim()
        val password = txtPwd.text.toString().trim()
        if (email.isEmpty()) {
            withContext(Dispatchers.Main) {
                txtUser.error = "El usuario es requerido"
                txtUser.requestFocus()
            }
            return
        }
        if (password.isEmpty()) {
            withContext(Dispatchers.Main) {
                txtPwd.error = "Se requiere la clave"
                txtPwd.requestFocus()
            }
            return
        }
        apiClient.getApiService(this)
            .verifyLogin(LoginRequest(email.toString(), password.toString()))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Error logging in
                    //t?.printStackTrace()
                    MyMessages.toast(applicationContext, t.toString())
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val loginResponse = response.body()

                    if (response.code() == 200 && loginResponse?.user != null) {
                        sessionManager.saveAuthToken(loginResponse.token)

                        Log.i(Constants.TAG_LOGS, "Token: " + Gson().toJson(loginResponse.token))
                        val intent = Intent(applicationContext, ListEmployeesActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)

                    } else {
                        MyMessages.toast(applicationContext, loginResponse?.message.toString())
                    }
                }
            })
    }
}
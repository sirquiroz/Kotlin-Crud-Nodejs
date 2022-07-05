package ec.edu.ups.appregistrousers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ec.edu.ups.appregistrousers.R
import ec.edu.ups.appregistrousers.data.requests.UserRequest
import ec.edu.ups.appregistrousers.data.responses.DefaultResponse
import ec.edu.ups.appregistrousers.data.services.ApiClient
import ec.edu.ups.appregistrousers.util.MyMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserCreateActivity : AppCompatActivity() {
    lateinit var txtName: EditText
    lateinit var txtApellido: EditText
    lateinit var txtEmail: EditText
    lateinit var btnAdd: Button
    lateinit var btnBack: Button
    lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_create)
        apiClient = ApiClient()
        txtApellido = findViewById(R.id.txtLastName)
        txtName = findViewById(R.id.txtName)
        txtEmail = findViewById(R.id.txtEmail)
        btnAdd = findViewById(R.id.btnAdd)
        btnBack = findViewById(R.id.btnRegresar)
        btnAdd.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                addCliente()
            }
        }
    }

    private suspend fun addCliente() {
        val name = txtName.text.toString().trim()
        val lastname = txtApellido.text.toString().trim()
        val email = txtEmail.text.toString().trim()
        if (name.isEmpty()) {
            withContext(Dispatchers.Main) {
                txtName.error = "Ingrese el nombre"
                txtName.requestFocus()
            }
            return
        }
        if (lastname.isEmpty()) {
            withContext(Dispatchers.Main) {
                txtApellido.error = "Ingrese el apellido"
                txtApellido.requestFocus()
            }
            return
        }
        //Utilizar el Apirest
        apiClient.getApiService(this)
            .addUser(UserRequest(name, lastname, email))
            .enqueue(object : Callback<DefaultResponse> {
                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    //Log.i("etiqueta","Aqui me encuentro")
                    val defaultResponse = response.body()
                    if (defaultResponse != null) {
                        if (response.code() == 200 && defaultResponse.error == false) {
                            MyMessage.toast(applicationContext, defaultResponse.message)
                            return
                        }
                    }
                    MyMessage.toast(applicationContext, "Error al procesar")
                }
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    MyMessage.toast(applicationContext, t.toString())
                }
            });//End enqueue
    }
    fun regresar(view: View){
        finish()
    }
}
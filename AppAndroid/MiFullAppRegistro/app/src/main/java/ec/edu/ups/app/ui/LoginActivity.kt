package ec.edu.ups.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import ec.edu.ups.app.R
import ec.edu.ups.app.data.model.Persona
import ec.edu.ups.app.data.model.userLogin
import ec.edu.ups.app.model.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    val TAG_LOGS = "proyectoMQ"

    lateinit var service: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.68.125:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create<ApiService>(ApiService::class.java)



    }
    fun getAllEmpleados(){
        //Recibimos todos los lista_personas
        service.getAllEmployees().enqueue(object: Callback<List<Persona>> {
            override fun onResponse(call: Call<List<Persona>>?, response: Response<List<Persona>>?) {
                val lista_personas = response?.body()
                Log.i(TAG_LOGS, Gson().toJson(lista_personas))
            }
            override fun onFailure(call: Call<List<Persona>>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }

    fun getEmpleadoById(){
        //Recibimos los datos del empleado con ID = 1
        var objPersona: Persona? = null
        service.getEmployeeById(3).enqueue(object: Callback<Persona>{
            override fun onResponse(call: Call<Persona>?, response: Response<Persona>?) {
                objPersona = response?.body()
                Log.i(TAG_LOGS, Gson().toJson(objPersona))
                Log.i(TAG_LOGS, objPersona.toString())
            }
            override fun onFailure(call: Call<Persona>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }

    fun editEmpleado(){
        var objPersona: Persona? = Persona(1, "Miguel", "mquir@mail.com", 99)
        //Editamos los datos por POST
        service.editEmployeeById(1, objPersona).enqueue(object: Callback<Persona>{
            override fun onResponse(call: Call<Persona>?, response: Response<Persona>?) {
                objPersona = response?.body()
                Log.i(TAG_LOGS, Gson().toJson(objPersona))


            }
            override fun onFailure(call: Call<Persona>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }


    fun verificationLogin(view: View){
        //Log.i("Login", "Log de Login")
        //Mensajes.alarmas(applicationContext, "Login en desarrollo")

        //val snack = Snackbar.make(view,"This is a simple Snackbar",Snackbar.LENGTH_LONG)
        //snack.show()
        //val intent = Intent(this, HomeActivity::class.java).apply {
            //putExtra(EXTRA_MESSAGE, message)
        //}
        //startActivity(intent)
        //getAllPosts()
        //editEmpleado()

        //Editamos los datos por POST
        service.verificalogin("admin","123").enqueue(object: Callback<String>{
            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                //val tex : String? = response?.body()
                Log.i(TAG_LOGS, response?.body().toString())
               // Log.i("Response", response.body().toString());

            }
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })


    }
    //https://github.com/ArisGuimera/RetrofitKotlinExample/tree/feature/2022updated/app/src/main/java/com/cursokotlin/retrofitkotlinexample varios codigos de ejemplos
    //https://www.youtube.com/watch?v=L3pM5YuxYp4 para probar los query
    //https://www.youtube.com/watch?v=cL3bXzUBFUA&t=1375s
    //https://github.com/probelalkhan/kotlin-retrofit-tutorial/blob/master/app/src/main/java/simplifiedcoding/net/kotlinretrofittutorial/storage/SharedPrefManager.kt
    //https://github.com/tirgei/RetrofitAuthorization/blob/master/app/src/main/java/com/tirgei/retrofitauthorization/utils/SessionManager.kt
    override fun onStart() {
        super.onStart()

       /* if(SharedPrefManager.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, ProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
        }*/
    }
}


/*
class LoginActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        apiClient.getApiService().login(LoginRequest(email = "s@sample.com", password = "mypassword"))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Error logging in
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val loginResponse = response.body()

                    if (loginResponse?.statusCode == 200 && loginResponse.user != null) {
                        sessionManager.saveAuthToken(loginResponse.authToken)
                    } else {
                        // Error logging in
                    }
                }
            })

    }
}



private fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getCharacterByName("$query/images").execute()
            val puppies = call.body() as DogsResponse?
            runOnUiThread {
                if (puppies?.status == "success") {
                    initCharacter(puppies)
                } else {
                    showErrorDialog()
                }
                hideKeyboard()
            }
        }
    }

    private fun showErrorDialog() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }




 */



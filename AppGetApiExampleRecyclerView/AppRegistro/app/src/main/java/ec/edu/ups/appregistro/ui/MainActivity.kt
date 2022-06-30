package ec.edu.ups.appregistro.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import ec.edu.ups.appregistro.R
import ec.edu.ups.appregistro.data.requests.LoginRequest
import ec.edu.ups.appregistro.data.responses.EmployeesResponse
import ec.edu.ups.appregistro.data.responses.LoginResponse
import ec.edu.ups.appregistro.util.Constants
import ec.edu.ups.appregistro.util.SessionManager
import ec.edu.ups.appregistro.data.ApiClient
import ec.edu.ups.appregistro.data.models.Employee
import ec.edu.ups.appregistro.util.RecyclerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager

    private lateinit var btnLogin: Button
    private lateinit var btnListar: Button
    var dataList = ArrayList<Employee>()
    lateinit var recyclerView: RecyclerView
    //lateinit var progerssProgressDialog: ProgressDialog
    lateinit var adapter:RecyclerAdapter
    override fun onDestroy() {
        super.onDestroy()
        // compositeDisposable!!.clear() // RxJava
        // job!!.cancel() // Coroutine
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiClient = ApiClient()
        sessionManager = SessionManager(this)

/*
        btnLogin = findViewById(R.id.btnLogin)
        btnListar = findViewById(R.id.btnListar)



        btnLogin.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                getlogin()
            }
        }
        btnListar.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                listEmployees()
            }
        }*/
        recyclerView = findViewById(R.id.recyclerview)

        //setting up the adapter
        //recyclerView.adapter= RecyclerAdapter(dataList,this)
        //recyclerView.layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        /*progerssProgressDialog=ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()*/


        CoroutineScope(Dispatchers.IO).launch {
            //getlogin()
           listEmployees()
        }


    }
    private var coinModel : ArrayList<Employee>? = null
    private lateinit var recyclerViewAdapter : RecyclerAdapter
    /**
     * Function to fetch posts
     */
    private suspend fun listEmployees() {
        apiClient.getApiService(this).fetchEmployees()
            .enqueue(object : Callback<EmployeesResponse> {
                override fun onFailure(call: Call<EmployeesResponse>, t: Throwable) {
                    t.printStackTrace()

                }

                override fun onResponse(call: Call<EmployeesResponse>, response: Response<EmployeesResponse>) {
                    val lista_personas = response?.body()?.employees
                    Log.i(Constants.TAG_LOGS, Gson().toJson(lista_personas))
                    if (lista_personas != null) {
                       // dataList.addAll(lista_personas)
                        //recyclerView.adapter?.notifyDataSetChanged()
/*

if (!response.isSuccessful) {
                    Log.d("Rezponse", response.message())
                    return
                }
                list = response.body()!!
                val adapter = Adapter(list)
                recyclerView.adapter = adapter



if(response.isSuccessful){
                    response.body()?.let {
                        coinModel = ArrayList(it)
                        coinModel?.let {
                            recyclerViewAdapter = RecyclerViewAdapter(coinModel!!)
                            binding.recyclerView.adapter = recyclerViewAdapter
                        }
                    }
                }
*
* */


                        recyclerView.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = RecyclerAdapter(lista_personas!!)

                        }                        //recyclerVi.setItems(list);
                        //adapter.notifyDataSetChanged();
                        //recyclerView.adapter = RecyclerAdapter(lista_personas, this@MainActivity)
                    }


                   // recyclerView.adapter.notifyDataSetChanged()
                }
            })
    }

    /*
    poner en el listado a q grafique esto
//https://github.com/cardaguh/RecyclerView-con-Retrofit-y-Kotlin/blob/master/app/src/main/java/com/cyclopsapps/kotlinrecyclerviewwithretrofit/view/MainActivity.kt
    private fun showData(users: List<User>){
        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter =
                UserAdapter(
                    users
                )
        }
    }*/
    private suspend fun getlogin(){
        //apiClient = ApiClient()
        //sessionManager = SessionManager(this)

        apiClient.getApiService(this).verifyLogin(LoginRequest(user = "admin", pwd = "123"))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Error logging in
                    t?.printStackTrace()
                }
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val loginResponse = response.body()

                    if (response.code() == 200 && loginResponse?.user != null) {
                        sessionManager.saveAuthToken(loginResponse.token)

                        //val lista_personas2 = response?.body()
                        Log.i(Constants.TAG_LOGS, Gson().toJson(loginResponse.token))
                        //Log.i(Constants.TAG_LOGS, Gson().toJson(lista_personas))
                    } else {
                        // Error logging in
                    }
                }
            })
    }
}
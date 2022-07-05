package ec.edu.ups.appregistro.ui.employee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import ec.edu.ups.appregistro.R
import ec.edu.ups.appregistro.data.adapters.RecyclerAdapter
import ec.edu.ups.appregistro.data.models.Employee
import ec.edu.ups.appregistro.data.responses.EmployeesResponse
import ec.edu.ups.appregistro.data.services.ApiClient
import ec.edu.ups.appregistro.ui.LoginActivity
import ec.edu.ups.appregistro.util.Constants
import ec.edu.ups.appregistro.util.MyMessages
import ec.edu.ups.appregistro.util.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListEmployeesActivity : AppCompatActivity() {
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager

    var dataList = ArrayList<Employee>()
    lateinit var recyclerView: RecyclerView
    //lateinit var progerssProgressDialog: ProgressDialog
    lateinit var adapter: RecyclerAdapter

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
        sessionManager.fetchAuthToken()?.let {
            if (it == null) {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                //MyMessages.toast(this, it)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    fun logout() {

        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_employees)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)
        recyclerView = findViewById(R.id.recycler_employees)
        CoroutineScope(Dispatchers.IO).launch {
            //getlogin()
            listEmployees()
        }

    }
    private suspend fun listEmployees() {
        apiClient.getApiService(this).getListEmployees()
            .enqueue(object : Callback<EmployeesResponse> {
                override fun onFailure(call: Call<EmployeesResponse>, t: Throwable) {
                    t.printStackTrace()

                }

                override fun onResponse(call: Call<EmployeesResponse>, response: Response<EmployeesResponse>) {
                    val lista_personas = response?.body()?.employees
                    Log.i("aqui.TAG_LOGS", Gson().toJson(lista_personas))
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
                            layoutManager = LinearLayoutManager(this@ListEmployeesActivity)
                            adapter = RecyclerAdapter(lista_personas!!)

                        }                        //recyclerVi.setItems(list);
                        //adapter.notifyDataSetChanged();
                        //recyclerView.adapter = RecyclerAdapter(lista_personas, this@MainActivity)
                    }else{
                        response?.body()?.let { MyMessages.toast(applicationContext, it.message) }
                    }


                    // recyclerView.adapter.notifyDataSetChanged()
                }
            })
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
            }
            R.id.navigation_home -> {
                //finish();
                //System.runFinalizersOnExit(true)
                //this.onBackPressed();
                //val editor = getSharedPreferences(Constants.SHARED_PREF_NAME, MODE_PRIVATE).edit()
                //editor.clear().apply()


                //this.onBackPressed();

                sessionManager.destroyAuthToken()
                logout()

            }
        }
    }


}
package ec.edu.ups.appregistrousers.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import ec.edu.ups.appregistrousers.R
import ec.edu.ups.appregistrousers.data.adapters.RecyclerAdapter
import ec.edu.ups.appregistrousers.data.models.User
import ec.edu.ups.appregistrousers.data.responses.UserResponse
import ec.edu.ups.appregistrousers.data.services.ApiClient
import ec.edu.ups.appregistrousers.util.MyMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var apiClient: ApiClient
    var dataList = ArrayList<User>()
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerAdapter

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            cargarDatos()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        apiClient = ApiClient()
        recyclerView = findViewById(R.id.recycler_users)

    }

    private suspend fun cargarDatos() {

        apiClient.getApiService(this).listUser()
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    //Log.i("Proyecto",response.body().toString())
                    val listaUsuarios = response.body()?.users
                    //Log.i("Proyecto",Gson().toJson(listaUsuario))
                    if (listaUsuarios != null) {
                        recyclerView.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = RecyclerAdapter(listaUsuarios)
                        }
                    } else {
                        MyMessage.toast(applicationContext, "No existen registro")
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    MyMessage.toast(applicationContext, t.toString())
                    //Log.i("Proyecto",t.toString())
                }

            });
    }

    fun menuHome(item: MenuItem) {
        when (item.getItemId()) {
            R.id.navigation_create_user -> {
                Toast.makeText(this, "Crear Nuevo Usuario", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, UserCreateActivity::class.java).apply {
                    putExtra("nombre", "miguel quiroz")
                }
                startActivity(intent)

            }
            R.id.navigation_exit -> {
                Toast.makeText(this, "Salir", Toast.LENGTH_SHORT).show()

                //val intent = Intent(this, UserCreateActivity::class.java)
                //intent.putExtra("nombre","miguel quiroz")
                //startActivity(intent)
            }
        }
    }
}
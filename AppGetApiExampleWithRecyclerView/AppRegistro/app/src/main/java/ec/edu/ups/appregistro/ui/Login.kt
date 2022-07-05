package ec.edu.ups.appregistro.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import ec.edu.ups.appregistro.R
import ec.edu.ups.appregistro.data.requests.LoginRequest
import ec.edu.ups.appregistro.data.responses.LoginResponse
import ec.edu.ups.appregistro.util.Constants
import ec.edu.ups.appregistro.util.SessionManager
import ec.edu.ups.appregistro.data.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        apiClient.getApiService(this).verifyLogin(LoginRequest(user = "admin", pwd = "123"))
            .enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Error logging in
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val loginResponse = response.body()

                    if (response.code() == 200 && loginResponse?.user != null) {
                        sessionManager.saveAuthToken(loginResponse.token)
                    } else {

                        Log.i(Constants.TAG_LOGS, Gson().toJson(loginResponse))
                    }
                }
            })

    }
}
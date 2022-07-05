package ec.edu.ups.appregistrousers.data.services

import ec.edu.ups.appregistrousers.data.requests.UserRequest
import ec.edu.ups.appregistrousers.data.responses.DefaultResponse
import ec.edu.ups.appregistrousers.data.responses.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("/")
    fun listUser(): Call<UserResponse>

    @POST("/create")
    fun addUser(@Body request: UserRequest): Call<DefaultResponse>
}
package ec.edu.ups.appregistro.data


import ec.edu.ups.appregistro.data.requests.EmployeeRequest
import ec.edu.ups.appregistro.util.Constants
import ec.edu.ups.appregistro.data.requests.LoginRequest
import ec.edu.ups.appregistro.data.responses.DefaultResponse
import ec.edu.ups.appregistro.data.responses.EmployeesResponse
import ec.edu.ups.appregistro.data.responses.LoginResponse

import retrofit2.Call
import retrofit2.http.*

/**
 * Interface for defining REST request functions
 */
interface ApiService {

    //@POST("/login")
    @POST(Constants.LOGIN_URL)
    fun verifyLogin(@Body request: LoginRequest): Call<LoginResponse>

    //@GET("/")
    @GET(Constants.EMPLOYEES_URL)
    fun fetchEmployees(): Call<EmployeesResponse>
    //fun fetchPosts(): Call<PostsResponse>

    //Agrega un nuevo empleado
    @POST("/add")
    fun addEmployee(@Body request: EmployeeRequest): Call<DefaultResponse>

    @DELETE("/delete/{id}")
    fun deleteEmployee(@Path("id") id: Int): Call<DefaultResponse>

    @PUT("/update/{id}")
    fun deleteEmployee(@Path("id") id: Int, @Body request: EmployeeRequest): Call<DefaultResponse>
/*

//https://gorest.co.in/public/v2/users
    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getUsersList(): Call<List<User>>

    //https://gorest.co.in/public/v2/users?name=a
    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun searchUsers(@Query("name") searchText: String): Call<List<User>>

    //https://gorest.co.in/public/v2/users/121
    @GET("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun getUser(@Path("user_id") user_id: String): Call<List<User>>

    @POST("users")
    @Headers(
        "Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer eb7f290f787b55e9b8847b49e13d5ed723443defa6d73514183d011a8b3a9ac2"
    )
    fun createUser(@Body param: User): Call<UserResponse>

    @PATCH("users/{user_id}")
    @Headers(
        "Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer eb7f290f787b55e9b8847b49e13d5ed723443defa6d73514183d011a8b3a9ac2"
    )
    fun updateUser(@Path("user_id") user_id: String, @Body params: User): Call<UserResponse>

@DELETE("users/{user_id}")
    @Headers(
        "Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer eb7f290f787b55e9b8847b49e13d5ed723443defa6d73514183d011a8b3a9ac2"
    )
    fun deleteUser(@Path("user_id") user_id: String): Call<UserResponse>
*
*/

}
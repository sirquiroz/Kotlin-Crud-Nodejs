package ec.edu.ups.app.model

import ec.edu.ups.app.config.Constants
import ec.edu.ups.app.data.model.Persona
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @GET("/")
    fun getAllEmployees(): Call<List<Persona>>

    @GET("get/{id}")
    fun getEmployeeById(@Path("id") id: Int): Call<Persona>

    @PUT("update/{id}")
    fun editEmployeeById(@Path("id") id: Int, @Body employee: Persona?): Call<Persona>

    @POST("add/")
    fun addEmployee( @Body employee: Persona?): Call<Persona>

    @GET("login/")
    fun verificalogin( @Body user: String, @Body pwd:String): Call<String>

    @DELETE("delete/{id}")
    fun deleteEmployee(@Path("id") id: Int): Call<Persona>
    @FormUrlEncoded
    @POST("createuser")
    fun createUser(
        @Field("email") email:String,
        @Field("name") name:String,
        @Field("password") password:String,
        @Field("school") school:String
    ):Call<DefaultResponse>

    @FormUrlEncoded
    @POST("userlogin")
    fun userLogin(
        @Field("email") email:String,
        @Field("password") password: String
    ):Call<LoginResponse>


    @POST(Constants.LOGIN_URL)
    @FormUrlEncoded
    fun login(@Body request: LoginRequest): Call<LoginResponse>



@POST("/posts")
@FormUrlEncoded
fun savePost(
    @Field("title") title: String?,
    @Field("body") body: String?,
    @Field("userId") userId: Long
): Call<LoginResponse?>

    @POST("/posts")
    @FormUrlEncoded
    fun savePost(@Body post: LoginResponse?): Call<LoginResponse?>?


}
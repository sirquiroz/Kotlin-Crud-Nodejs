package ec.edu.ups.app.model


import ec.edu.ups.app.data.LoginRequest
import ec.edu.ups.app.data.LoginResponse
import ec.edu.ups.app.data.PersonaResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    /*@FormUrlEncoded
    @POST("login/")
    suspend fun getLogin(
        @Field("email") email: LoginRequest,
        @Field("pwd2") pwd2: String
    ): Call<LoginResponse>
*/
    //suspend fun getPost(@Header("Auth") auth: String): Response<Post>
    @POST("login/")
    fun getLogin(@Body loginRequest: LoginRequest): Call<LoginResponse>
    /*@FormUrlEncoded
    @POST("login")
    fun getLogin(
        @Field("email") email:String,
        @Field("pwd") password: String
    ):Call<LoginResponse>*/
/*
*
 @FormUrlEncoded
    @POST("createuser")
    fun createUser(
            @Field("email") email:String,
            @Field("name") name:String,
            @Field("password") password:String,
            @Field("school") school:String
    ):Call<DefaultResponse>

* */

    @GET("/")
    fun getAllEmployees(@Header("Authorization") auth: String): Call<List<PersonaResponse>>
   /* @GET("/")
    fun getAllEmployees(): Call<List<Persona>>
*/
    @GET("get/{id}")
    fun getEmployeeById(@Path("id") id: Int): Call<PersonaResponse>

    @PUT("update/{id}")
    fun editEmployeeById(@Path("id") id: Int, @Body employee: PersonaResponse?): Call<PersonaResponse>

    @POST("add/")
    fun addEmployee(@Body employee: PersonaResponse?): Call<PersonaResponse>
/*
    @GET("login/")
    fun verificalogin(@Body user: String, @Body pwd: String): Call<String>

    @DELETE("delete/{id}")
    fun deleteEmployee(@Path("id") id: Int): Call<Persona>

    @FormUrlEncoded
    @POST("createuser")
    fun createUser(
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("password") password: String,
        @Field("school") school: String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("userlogin")
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>


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
    */

}
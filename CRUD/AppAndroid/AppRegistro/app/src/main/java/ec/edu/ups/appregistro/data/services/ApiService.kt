package ec.edu.ups.appregistro.data.services


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
 * https://cursokotlin.com/tutorial-retrofit-2-en-kotlin-con-corrutinas-consumiendo-api-capitulo-20-v2/
 */
interface ApiService {
    //@POST("/login")
    @POST(Constants.LOGIN_URL)
    fun verifyLogin(@Body request: LoginRequest): Call<LoginResponse>


    //@GET(Constants.EMPLOYEES_URL)
    @Headers("Accept:application/json", "Content-Type:application/json")
    @GET("/")
    fun getListEmployees(): Call<EmployeesResponse>
    //fun getEmployeesList(): Call<List<EmployeesResponse>>

    /*@Headers(
        "Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer eb7f290f787b55e9b8847b49e13d5ed723443defa6d73514183d011a8b3a9ac2"
    )*/
    @POST("/add_employee")
    fun addEmployee(@Body request: EmployeeRequest): Call<DefaultResponse>

    @PUT("/update_employee/{id}")
    fun updateEmployee(@Path("id") id: Int, @Body request: EmployeeRequest): Call<DefaultResponse>

    @DELETE("/delete_employee/{emp_id}")
    fun deleteEmployee(@Path("emp_id") emp_id: Int): Call<DefaultResponse>

    //https://gorest.co.in/public/v2/users?name=a
    @GET("employees")
    @Headers("Accept:application/json", "Content-Type:application/json")
    fun searchEmployees(@Query("name") searchText: String): Call<List<EmployeeRequest>>
}
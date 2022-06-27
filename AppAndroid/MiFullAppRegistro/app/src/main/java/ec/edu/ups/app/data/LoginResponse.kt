package ec.edu.ups.app.data

import com.google.gson.annotations.SerializedName
/*
data class LoginResponse (
    @SerializedName("status")
    var status : Int,
    @SerializedName("message")
    var message: String,
    @SerializedName("user")
    var user: authData,
    @SerializedName("token")
    var token: String
) {
    data class authData (
        @SerializedName("id")
        var id : Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("email")
        var email: String,
        @SerializedName("islogged")
        var islogged: Boolean,
        @SerializedName("iat")
        var iat: String,
        @SerializedName("exp")
        var exp: String
    )
}
class LoginResponse(
    @SerializedName("id")
    val error: Boolean=false,
    @SerializedName("message")
    val message:String? = null,
    @SerializedName("user")
    val user: Usuario? = null)

 */
data class LoginResponse(
    val error: Boolean=false,
    val islogged: Boolean=false,
    val message:String? = null,
    val token: String,
    val user: Usuario? = null)
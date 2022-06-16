package ec.edu.ups.app.model

import com.google.gson.annotations.SerializedName

data class User2(
    val id:Int,
    val email:String,
    val name:String,
    val edad:String
    )
data class User (
    @SerializedName("id")
    var id: String,

    @SerializedName("first_name")
    var firstName: String,

    @SerializedName("last_name")
    var lastName: String,

    @SerializedName("email")
    var email: String
)

data class LoginRequest (
    @SerializedName("email")
    var email: String,

    @SerializedName("password")
    var password: String
)
data class LoginResponse (
    @SerializedName("status_code")
    var statusCode: Int,

    @SerializedName("auth_token")
    var authToken: String,

    @SerializedName("user")
    var user: User
)


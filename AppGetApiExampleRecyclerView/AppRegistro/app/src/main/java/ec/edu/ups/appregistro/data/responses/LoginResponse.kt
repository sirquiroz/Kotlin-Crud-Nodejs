package ec.edu.ups.appregistro.data.responses

import com.google.gson.annotations.SerializedName
import ec.edu.ups.appregistro.data.models.User


/*
data class LoginResponse (
    @SerializedName("status_code")
    var statusCode: Int,

    @SerializedName("token")
    var token: String,

    @SerializedName("user")
    var user: Usuario?
)*/
data class LoginResponse(
    var error: Boolean=false,
    var islogged: Boolean=false,
    var message:String? = null,
    var token: String,

    @SerializedName("user")
    var user: User?
    )
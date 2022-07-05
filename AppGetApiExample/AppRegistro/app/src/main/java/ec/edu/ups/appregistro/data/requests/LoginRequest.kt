package ec.edu.ups.appregistro.data.requests

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("email")
    var user: String,

    @SerializedName("pwd")
    var pwd: String
)
package ec.edu.ups.app.data

import com.google.gson.annotations.SerializedName

class LoginRequest(
    @SerializedName("email")
    var user: String,
    @SerializedName("pwd")
    var pwd: String,
    /*@SerializedName("islogged")
    var islogged: Boolean*/
)
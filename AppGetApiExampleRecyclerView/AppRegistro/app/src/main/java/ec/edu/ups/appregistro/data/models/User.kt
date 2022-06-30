package ec.edu.ups.appregistro.data.models

import com.google.gson.annotations.SerializedName

/*data class User (
    @SerializedName("id")
    var id: String,

    @SerializedName("first_name")
    var firstName: String,

    @SerializedName("last_name")
    var lastName: String,

    @SerializedName("email")
    var email: String
)*/
data class User(
    val id: Int,
    val email: String?,
    val name: String?,
    val pwd: String?,
    val iat: String?,
    val exp: String?
)
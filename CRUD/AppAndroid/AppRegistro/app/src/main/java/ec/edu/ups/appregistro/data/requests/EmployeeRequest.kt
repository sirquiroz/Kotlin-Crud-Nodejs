package ec.edu.ups.appregistro.data.requests

import com.google.gson.annotations.SerializedName

data class EmployeeRequest(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("edad")
    var edad: Int,

    @SerializedName("email")
    var email: String
)
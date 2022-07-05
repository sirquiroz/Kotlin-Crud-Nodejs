package ec.edu.ups.appregistro.data.models

import com.google.gson.annotations.SerializedName

data class Employee(
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("edad")
    var edad: Int
)
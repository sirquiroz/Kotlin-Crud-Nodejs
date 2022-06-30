package ec.edu.ups.appregistro.data.responses
import com.google.gson.annotations.SerializedName
import ec.edu.ups.appregistro.data.models.Employee


data class EmployeesResponse(
    @SerializedName("status_code")
    var status: Int,

    @SerializedName("message")
    var message: String,

    @SerializedName("employees")
    var employees: List<Employee>
)
package ec.edu.ups.appregistro.util
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import ec.edu.ups.appregistro.R


//import ec.edu.ups.ejemplojwtconsumer.data.models.User

/**
 * Session manager to save and fetch data from SharedPreferences
 */
class SessionManager (context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(Constants.SHARED_PREF_NAME), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = Constants.USER_TOKEN
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        //fun saveAuthToken(token: String,user: Usuario) {
            val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        //editor.putString("usuario", user.toString())

        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
    /**
     * Function to destroy auth token
     */
    fun destroyAuthToken() {
        val editor = prefs.edit()
        editor.clear().apply()
    }
}
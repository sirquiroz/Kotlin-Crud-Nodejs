package ec.edu.ups.app.config

import android.content.Context
import android.content.SharedPreferences
import ec.edu.ups.app.R

//https://medium.com/android-news/token-authorization-with-retrofit-android-oauth-2-0-747995c79720
////https://github.com/AldemirGomesDev/Curso_Android_Kotlin/
//https://github.com/probelalkhan/kotlin-retrofit-tutorial
//class SharedPrefManager private constructor(private val mCtx: Context) {}
class SessionManager(context: Context?) {

    private var prefs: SharedPreferences =
        context!!.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }


}




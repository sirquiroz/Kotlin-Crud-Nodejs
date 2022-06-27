package ec.edu.ups.app.config

import android.content.Context
import ec.edu.ups.app.data.Usuario


class SharedPrefManager private constructor(private val mCtx: Context) {

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }

    val user: Usuario
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return Usuario(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("pwd", null),
                sharedPreferences.getString("iat", null),
                sharedPreferences.getString("exp", null)
            )
        }

    fun saveUser(user: Usuario) {

        val sharedPreferences = mCtx.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt("id", user.id)
        editor.putString("email", user.email)
        editor.putString("name", user.name)
        editor.putString("pwd", user.pwd)
        editor.putString("iat", user.iat)
        editor.putString("exp", user.exp)

        editor.apply()

    }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        //private val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: SharedPrefManager? = null

        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

}
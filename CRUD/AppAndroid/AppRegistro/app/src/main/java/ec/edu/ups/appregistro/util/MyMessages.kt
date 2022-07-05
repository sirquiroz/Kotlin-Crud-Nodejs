package ec.edu.ups.appregistro.util

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MyMessages {
    companion object {
        fun toast(context: Context, msn: String) {
            val myToast = Toast.makeText(context, msn, Toast.LENGTH_LONG)
            //myToast.setGravity(Gravity.LEFT, 200, 200)
            myToast.show()
        }

        fun snackBar(context: View, msn: String) {
            val snack = Snackbar.make(context, msn, Snackbar.LENGTH_SHORT)
            snack.show()
        }
    }
    //val intent = Intent(this, HomeActivity::class.java).apply {
    //putExtra(EXTRA_MESSAGE, message)
    //}
    //startActivity(intent)
}

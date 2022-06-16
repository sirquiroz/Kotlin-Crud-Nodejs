package ec.edu.ups.app.ui

import android.content.Context
import android.view.Gravity
import android.widget.Toast

class Mensajes {
    companion object {
        fun alarmas(context: Context, msn: String) {
            val myToast = Toast.makeText(context, msn, Toast.LENGTH_LONG)
            myToast.setGravity(Gravity.LEFT,200,200)
            myToast.show()
        }
    }
}

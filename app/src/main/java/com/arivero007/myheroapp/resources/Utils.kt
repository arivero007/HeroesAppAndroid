package com.arivero007.myheroapp.resources

import android.content.Context
import androidx.appcompat.app.AlertDialog
import java.security.MessageDigest

class Utils {

    companion object{

        fun getMD5(md5: String): String {
            val bytes = MessageDigest.getInstance("MD5").digest(md5.toByteArray())
            return bytes.joinToString("") {
                "%02x".format(it)
            }
        }

        fun showAlert(context: Context, text: String){
            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setMessage(text)
            alertDialogBuilder.setPositiveButton("Close"){_, _ ->
                // Do something when user press the positive button
            }
            alertDialogBuilder.show()
        }
    }
}
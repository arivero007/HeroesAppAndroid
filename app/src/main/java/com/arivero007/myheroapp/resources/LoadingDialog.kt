package com.arivero007.myheroapp.resources

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.arivero007.myheroapp.R

class LoadingDialog(private var activity: Activity) {

    lateinit var dialog: AlertDialog

    companion object {
        @Volatile
        private var INSTANCE: LoadingDialog? = null
        fun getInstance(activity: Activity) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: LoadingDialog(activity).also {
                    INSTANCE = it
                }
            }
    }

    fun startLoadingDialog(){
        if(!this::dialog.isInitialized){
            val builder = AlertDialog.Builder(activity)
            val inflater = activity.layoutInflater
            builder.setView(inflater.inflate(R.layout.loading_dialog, null))
            builder.setCancelable(false)

            dialog = builder.create()
            dialog.show()
        }else{
            if(!dialog.isShowing){
                dialog.show()
            }
        }
    }

    fun dismissLoadingDialog(){
        if(this::dialog.isInitialized){
            if(dialog.isShowing){
                dialog.dismiss()
            }
        }
    }

}
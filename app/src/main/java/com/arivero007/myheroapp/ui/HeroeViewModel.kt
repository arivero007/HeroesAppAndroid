package com.arivero007.myheroapp.ui

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arivero007.myheroapp.R
import com.arivero007.myheroapp.network.RetrofitBuilder
import com.arivero007.myheroapp.resources.*
import com.arivero007.myheroapp.resources.Utils.Companion.addApiKeys
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeroeViewModel: ViewModel() {

    private val tagName = "HeroeActivity: "

    private val _heroe = MutableLiveData<Heroe>()
    val heroe: LiveData<Heroe>
        get() = _heroe

    fun setHeroe(heroe: Heroe){
        _heroe.value = heroe
    }

    //REST
    fun downloadHeroe(heroeId: Int, activity: Activity){

        val ts = System.currentTimeMillis()/1000
        val hash = Utils.getMD5(ts.toString().addApiKeys())

        LoadingDialog.getInstance(activity).startLoadingDialog()

        val retrofit = RetrofitBuilder
        retrofit.apiService.getHeroeInfo(heroeId, ts.toString(), Constants.apiKeyPu, hash).enqueue(
                object: Callback<HeroesList> {
                    override fun onResponse(call: Call<HeroesList>, response: Response<HeroesList>) {
                        val res = response.body()
                        Log.d(tagName, "Response correct!")
                        if (res != null){
                            if(res.data.results.count() > 0){
                                _heroe.value = res.data.results.first()
                            }else{
                                Utils.showAlert(activity, activity.getString(R.string.no_data))
                            }
                        }
                    }

                    override fun onFailure(call: Call<HeroesList>, t: Throwable) {
                        Log.d(tagName, "Web Service failed!")
                    }

                })
    }
}
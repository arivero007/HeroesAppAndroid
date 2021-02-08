package com.arivero007.myheroapp.ui

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.arivero007.myheroapp.network.RetrofitBuilder
import com.arivero007.myheroapp.resources.Constants
import com.arivero007.myheroapp.resources.HeroesList
import com.arivero007.myheroapp.resources.LoadingDialog
import com.arivero007.myheroapp.resources.Utils
import com.arivero007.myheroapp.resources.Utils.Companion.addApiKeys
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeroesListViewModel: ViewModel() {

    private val tagName = "HeroesListViewModel: "

    private val _heroes = MutableLiveData<HeroesList>()
    val heroes: LiveData<HeroesList>
        get() = _heroes

    fun setHeroes(heroes: HeroesList) {
        _heroes.value = heroes
    }

    //REST
    fun downloadListOfHeroes(activity: Activity){

        val ts = System.currentTimeMillis()/1000
        val hash = Utils.getMD5(ts.toString().addApiKeys())

        LoadingDialog.getInstance(activity).startLoadingDialog()

        val retrofit = RetrofitBuilder
        retrofit.apiService.getListOHeroes(ts.toString(), Constants.apiKeyPu, hash).enqueue(
                object: Callback<HeroesList> {
                    override fun onResponse(call: Call<HeroesList>, response: Response<HeroesList>) {
                        val res = response.body()

                        Log.d(tagName, "Response correct!")
                        if (res != null){
                            _heroes.value = res
                        }
                        LoadingDialog.getInstance(activity).dismissLoadingDialog()
                    }

                    override fun onFailure(call: Call<HeroesList>, t: Throwable) {
                        //Log.d(TAG, "Web Service failed!")
                        LoadingDialog.getInstance(activity).dismissLoadingDialog()
                    }

                })
    }


}
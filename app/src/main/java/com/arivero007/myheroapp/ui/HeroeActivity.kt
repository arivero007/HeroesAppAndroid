package com.arivero007.myheroapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.arivero007.myheroapp.R
import com.arivero007.myheroapp.databinding.ActivityHeroeBinding
import com.arivero007.myheroapp.network.RetrofitBuilder
import com.arivero007.myheroapp.resources.*
import com.arivero007.myheroapp.resources.Utils.Companion.addApiKeys
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeroeActivity : AppCompatActivity() {

    private val tagName = "HeroeActivity: "

    private val heroeModel: HeroeViewModel by viewModels()
    private lateinit var binding: ActivityHeroeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        receiveData()
        LoadingDialog.getInstance(this).startLoadingDialog()

        heroeModel.heroe.observe(this, {
            if(it != null){
                setHeroeData()
            }else{
                LoadingDialog.getInstance(this).dismissLoadingDialog()
                Utils.showAlert(this, getString(R.string.no_data))
            }
        })
    }

    //Data Manager
    private fun receiveData(){
        //Load data if information received
        if(intent.hasExtra("heroeId")){
            val heroeId = intent.getIntExtra("heroeId", 0)
            heroeModel.downloadHeroe(heroeId, this)
        }else{
            Utils.showAlert(this, getString(R.string.no_data))
        }
    }

    private fun setHeroeData(){

        binding.heroeName.text = heroeModel.heroe.value?.name ?: getString(R.string.no_hero_name)
        val imgUrl = heroeModel.heroe.value?.thumbnail?.path + "." + heroeModel.heroe.value?.thumbnail?.extension
        Picasso.get().setIndicatorsEnabled(true)
        Picasso.get().load(imgUrl).into(binding.heroeImage)
        binding.heroeDescription.text = heroeModel.heroe.value?.description ?: getString(R.string.no_hero_description)
        binding.heroeModified.text = heroeModel.heroe.value?.modified
        LoadingDialog.getInstance(this).dismissLoadingDialog()
    }

}
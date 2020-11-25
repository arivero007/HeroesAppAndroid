package com.arivero007.myheroapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arivero007.myheroapp.resources.Heroe

class HeroeViewModel: ViewModel() {

    private val _heroe = MutableLiveData<Heroe>()
    val heroe: LiveData<Heroe>
        get() = _heroe

    fun setHeroe(heroe: Heroe){
        _heroe.value = heroe
    }
}
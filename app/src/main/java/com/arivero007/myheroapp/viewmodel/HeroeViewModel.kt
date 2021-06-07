package com.arivero007.myheroapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arivero007.myheroapp.model.Heroe
import com.arivero007.myheroapp.repositories.HeroeRepository
import com.arivero007.myheroapp.repositories.HeroesRepository

class HeroeViewModel(
    private val repository: HeroeRepository
    ): ViewModel() {

    private val _heroe = MutableLiveData<Heroe>()
    val heroe: LiveData<Heroe>
        get() = _heroe

    fun setHeroe(heroe: Heroe){
        _heroe.value = heroe
    }
}
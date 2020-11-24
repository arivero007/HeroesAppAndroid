package com.arivero007.myheroapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arivero007.myheroapp.resources.HeroesList

class HeroesViewModel: ViewModel() {

    private val _heroes = MutableLiveData<HeroesList>()
    val heroes: LiveData<HeroesList>
        get() = _heroes

    fun setHeroes(heroes: HeroesList) {
        _heroes.value = heroes
    }

}
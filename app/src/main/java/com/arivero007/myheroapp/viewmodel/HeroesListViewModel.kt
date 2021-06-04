package com.arivero007.myheroapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arivero007.myheroapp.model.HeroesList
import com.arivero007.myheroapp.repositories.HeroesRepository

class HeroesListViewModel(
    private val repository: HeroesRepository
): ViewModel() {

    private val _heroes = MutableLiveData<HeroesList>()
    val heroes: LiveData<HeroesList>
        get() = _heroes

    fun setHeroes(heroes: HeroesList) {
        _heroes.value = heroes
    }

}
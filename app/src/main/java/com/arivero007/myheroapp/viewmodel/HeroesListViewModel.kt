package com.arivero007.myheroapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arivero007.myheroapp.model.HeroesList
import com.arivero007.myheroapp.network.Resource
import com.arivero007.myheroapp.repositories.HeroesRepository
import kotlinx.coroutines.launch

class HeroesListViewModel(
    private val repository: HeroesRepository
): ViewModel() {

    private val _charactersResponse : MutableLiveData<Resource<HeroesList>> = MutableLiveData()
    val charactersResponse : LiveData<Resource<HeroesList>>
        get() = _charactersResponse

    private val _heroes = MutableLiveData<HeroesList>()
    val heroes: LiveData<HeroesList>
        get() = _heroes

    fun setHeroes(heroes: HeroesList) {
        _heroes.value = heroes
    }

    fun getCharacterList() = viewModelScope.launch {
        _charactersResponse.value = repository.getAllCharacters()
    }
}
package com.arivero007.myheroapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arivero007.myheroapp.model.CharactersList
import com.arivero007.myheroapp.data.network.Resource
import com.arivero007.myheroapp.data.repositories.CharactersRepository
import kotlinx.coroutines.launch

class CharactersListViewModel(
    private val repository: CharactersRepository
): ViewModel() {

    private val _charactersResponse : MutableLiveData<Resource<CharactersList>> = MutableLiveData()
    val charactersResponse : LiveData<Resource<CharactersList>>
        get() = _charactersResponse

    private val _heroes = MutableLiveData<CharactersList>()
    val characters: LiveData<CharactersList>
        get() = _heroes

    fun setHeroes(characters: CharactersList) {
        _heroes.value = characters
    }

    fun getCharacterList() = viewModelScope.launch {
        _charactersResponse.value = repository.getAllCharacters()
    }

    fun saveCharacterId(id: Int) = viewModelScope.launch {
        repository.saveCharacterId(id)
    }
}
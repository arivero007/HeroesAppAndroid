package com.arivero007.myheroapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arivero007.myheroapp.data.network.Resource
import com.arivero007.myheroapp.data.repositories.CharacterRepository
import com.arivero007.myheroapp.model.CharactersList
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val repository: CharacterRepository
    ): ViewModel() {

    private val _characterResponse = MutableLiveData<Resource<CharactersList>>()
    val characterResponse: LiveData<Resource<CharactersList>>
        get() = _characterResponse

    suspend fun getCharacterId(): Int = repository.getCharacterId()

    fun getCharacterInfo(id: Int) = viewModelScope.launch {
        _characterResponse.value = repository.getCharacterInfo(id)
    }
}
package com.arivero007.myheroapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arivero007.myheroapp.data.repositories.BaseRepository
import com.arivero007.myheroapp.data.repositories.CharacterRepository
import com.arivero007.myheroapp.data.repositories.CharactersRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory (
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CharactersListViewModel::class.java) -> CharactersListViewModel(repository as CharactersRepository) as T
            modelClass.isAssignableFrom(CharacterViewModel::class.java) -> CharacterViewModel(repository as CharacterRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass not found")
        }
    }

}
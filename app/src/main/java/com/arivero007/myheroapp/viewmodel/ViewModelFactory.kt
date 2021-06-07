package com.arivero007.myheroapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arivero007.myheroapp.repositories.BaseRepository
import com.arivero007.myheroapp.repositories.HeroeRepository
import com.arivero007.myheroapp.repositories.HeroesRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory (
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HeroesListViewModel::class.java) -> HeroesListViewModel(repository as HeroesRepository) as T
            modelClass.isAssignableFrom(HeroeViewModel::class.java) -> HeroeViewModel(repository as HeroeRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass not found")
        }
    }

}
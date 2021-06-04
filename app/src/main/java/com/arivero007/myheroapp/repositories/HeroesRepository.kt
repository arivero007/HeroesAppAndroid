package com.arivero007.myheroapp.repositories

import com.arivero007.myheroapp.network.ApiService

class HeroesRepository(
    private val api: ApiService
): BaseRepository() {

    suspend fun getAllCharacters() = safeApiCall {
        api.getListOHeroes()
    }
}
package com.arivero007.myheroapp.repositories

import android.provider.SyncStateContract
import com.arivero007.myheroapp.network.ApiService
import com.arivero007.myheroapp.resources.Constants

class HeroesRepository(
    private val api: ApiService
): BaseRepository() {

    suspend fun getAllCharacters() = safeApiCall {
        val dict = generateHashData()
        api.getListOfHeroes(dict.ts, Constants.apiKeyPu, dict.hash)
    }
}
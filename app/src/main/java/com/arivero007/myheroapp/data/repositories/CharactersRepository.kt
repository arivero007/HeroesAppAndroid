package com.arivero007.myheroapp.data.repositories

import com.arivero007.myheroapp.data.UserPreferences
import com.arivero007.myheroapp.data.network.ApiService
import com.arivero007.myheroapp.resources.Constants

class CharactersRepository(
    private val api: ApiService,
    private val prefs: UserPreferences
): BaseRepository() {

    suspend fun getAllCharacters() = safeApiCall {
        val dict = generateHashData()
        api.getListOfHeroes(dict.ts, Constants.apiKeyPu, dict.hash)
    }

    suspend fun saveCharacterId(id: Int){
        prefs.saveCharacterId(id)
    }
}
package com.arivero007.myheroapp.data.repositories

import com.arivero007.myheroapp.data.UserPreferences
import com.arivero007.myheroapp.data.network.ApiService
import com.arivero007.myheroapp.resources.Constants
import kotlinx.coroutines.flow.first

class CharacterRepository(
    private val api: ApiService,
    private val prefs: UserPreferences
): BaseRepository() {

    suspend fun getCharacterId(): Int = prefs.characterIdFlow.first()

    suspend fun getCharacterInfo(id: Int) = safeApiCall {
        val dict = generateHashData()
        api.getHeroeInfo(id, dict.ts, Constants.apiKeyPu, dict.hash)
    }
}
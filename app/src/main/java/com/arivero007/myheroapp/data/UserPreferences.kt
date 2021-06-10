package com.arivero007.myheroapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("MarvelDataStore")

class UserPreferences(
    context: Context
) {
    private val dataStore: DataStore<Preferences> = context.dataStore

    private val CHARACTER_ID = intPreferencesKey("key_character_id")

    val characterIdFlow: Flow<Int> = dataStore.data.map {
        it[CHARACTER_ID] ?: 0
    }

    suspend fun saveCharacterId(character: Int){
        dataStore.edit {
            it[CHARACTER_ID] = character
        }
    }

    suspend fun clearPreferences(){
        dataStore.edit {
            it.clear()
        }
    }

}
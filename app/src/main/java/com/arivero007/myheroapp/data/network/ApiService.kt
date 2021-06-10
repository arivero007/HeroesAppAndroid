package com.arivero007.myheroapp.data.network

import com.arivero007.myheroapp.model.CharactersList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.*

interface ApiService {

    @GET("public/characters")
    suspend fun getListOfHeroes(
        @Query("ts") ts: String?,
        @Query("apikey") apikey: String?,
        @Query("hash") hash: String?
    ): CharactersList

    @GET("public/characters/{characterId}")
    suspend fun getHeroeInfo(
        @Path("characterId") postId: Int,
        @Query("ts") ts: String?,
        @Query("apikey") apikey: String?,
        @Query("hash") hash: String?
    ): CharactersList
}
package com.arivero007.myheroapp.network

import com.arivero007.myheroapp.model.HeroesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("public/characters")
    suspend fun getListOHeroes(
    ): Call<HeroesList>

    @GET("public/characters/{characterId}")
    suspend fun getHeroeInfo(
        @Path("characterId") postId: Int,
    ): Call<HeroesList>
}
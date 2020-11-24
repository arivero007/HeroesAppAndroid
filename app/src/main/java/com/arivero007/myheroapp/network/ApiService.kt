package com.arivero007.myheroapp.network

import com.arivero007.myheroapp.resources.HeroesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("public/characters")
    fun getListOHeroes(@Query("ts") ts: String?, @Query("apikey") apikey: String?, @Query("hash") hash: String?): Call<HeroesList>
}
package com.arivero007.myheroapp.resources

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HeroesList(
    @SerializedName("data")
    val data: Characters
):Serializable

data class Characters(
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val results: List<Heroe>
):Serializable

data class Heroe(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail
): Serializable

data class Thumbnail(
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String
): Serializable


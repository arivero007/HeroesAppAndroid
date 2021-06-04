package com.arivero007.myheroapp.model

import java.io.Serializable

data class HeroesList(
    val data: Characters
):Serializable

data class Characters(
    val count: Int,
    val results: List<Heroe>
):Serializable

data class Heroe(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail
): Serializable

data class Thumbnail(
    val path: String,
    val extension: String
): Serializable


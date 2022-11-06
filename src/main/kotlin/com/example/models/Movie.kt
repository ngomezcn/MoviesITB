package com.example.models

import kotlinx.serialization.Serializable
import java.util.*

var globalId = 0

@Serializable
data class Movie(var title: String, var year: String, var gender: String, var director: String, var image: String = "") {
    val id: String

    init {
        globalId += 1
        id = globalId.toString()
    }
}

val movieStorage = mutableListOf<Movie>()
val moviesPath = "D:/GitRepos/MoviesITB/src/main/resources/static/images/movies/"

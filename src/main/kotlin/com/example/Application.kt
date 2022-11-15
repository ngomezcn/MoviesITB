package com.example

import com.example.models.Comment
import com.example.models.Movie
import com.example.models.commentStorage
import com.example.models.movieStorage
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*


fun main() {

    // movieStorage.add(Movie("1", "Hobbit", "2016", "FPS", "BugiSoft", "baixa.jpeg"))
    //movieStorage.add(Movie("2", "Interestelar", "2016", "FPS", "BugiSoft", "baixa1.jpeg"))
    movieStorage.add(Movie( "Alien", "2016", "Action", "Peter Jackson", "alien.jpg"))
    movieStorage.add(Movie( "Hobbit", "2011", "Adventure", "Willyrex", "hobbit.jpeg"))
    commentStorage.add(Comment("1", "1","Soy un comentario", "03/09/2022", ))

    println(movieStorage[0])
    println(commentStorage[0])

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureSerialization()
        configureRouting()
    }.start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
}
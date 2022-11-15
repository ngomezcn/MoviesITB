package com.example.plugins

import com.example.routes.api.apiFilmsRouting
import com.example.routes.web.webFilmsRouting
import io.ktor.http.ContentDisposition.Companion.File
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import java.io.File

fun Application.configureRouting() {

    routing {
        resource("/", "index.html")
        resource("*", "index.html")

        static("static") {
            resources("static")
        }


        apiFilmsRouting()
        webFilmsRouting()
    }
}

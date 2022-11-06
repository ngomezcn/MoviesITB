package com.example.plugins

import com.example.routes.api.apiFilmsRouting
import com.example.routes.web.webFilmsRouting
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*

fun Application.configureRouting() {

    routing {
        static("/static") {
            resources("static")
        }
        apiFilmsRouting()
        webFilmsRouting()
    }
}

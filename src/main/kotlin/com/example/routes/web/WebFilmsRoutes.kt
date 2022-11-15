package com.example.routes.web

import com.example.enums.RoutesEnum
import com.example.models.movieStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.webFilmsRouting() {

    route("/") {
        get("/") {
            call.respondRedirect("/home")
        }

        // HOME
        get(RoutesEnum.home.route) {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = RoutesEnum.home.route
                this.sTitle = RoutesEnum.home.title
            }
        }

        // ALL
        get(RoutesEnum.all.route) {

            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = RoutesEnum.all.route
                this.sTitle = RoutesEnum.all.title
            }
        }

        //NEW
        get(RoutesEnum.new.route) {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = RoutesEnum.new.route
                this.sTitle = RoutesEnum.new.title
            }
        }

        //DETAIL
        get(RoutesEnum.detail.route) {

            val exmovieId = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.moveId = exmovieId
                this.content = RoutesEnum.detail.route
                this.sTitle = RoutesEnum.detail.title

            }
        }

        //ABOUT US
        get(RoutesEnum.about.route) {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = RoutesEnum.about.route
                this.sTitle = RoutesEnum.about.title


            }
        }

        get("delete/id/{id?}") {
            val exmovieId = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            movieStorage.removeIf { it.id == exmovieId }

            call.respondRedirect("/all")
        }

        //ABOUT US
        get(RoutesEnum.about.route) {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = RoutesEnum.about.route
                this.sTitle = RoutesEnum.about.title


            }
        }
    }
}
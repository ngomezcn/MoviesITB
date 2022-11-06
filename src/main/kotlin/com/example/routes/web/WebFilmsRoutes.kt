package com.example.routes.web

import com.example.enums.RoutesTitles
import com.example.models.movieStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import java.nio.file.Paths

fun Route.webFilmsRouting() {

    route("/") {
        get("/") {
            call.respondRedirect("/home")
        }

        // HOME
        get(RoutesTitles.home.route) {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = RoutesTitles.home.route
                this.sTitle = RoutesTitles.home.title
            }
        }

        // ALL
        get(RoutesTitles.all.route) {

            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = RoutesTitles.all.route
                this.sTitle = RoutesTitles.all.title
            }
        }

        //NEW
        get(RoutesTitles.new.route) {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = RoutesTitles.new.route
                this.sTitle = RoutesTitles.new.title
            }
        }

        //DETAIL
        get(RoutesTitles.detail.route) {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = RoutesTitles.detail.route
                this.sTitle = RoutesTitles.detail.title
            }
        }

        //ABOUT US
        get(RoutesTitles.about.route) {
            call.respondHtmlTemplate(LayoutTemplate()) {
                this.content = RoutesTitles.about.route
                this.sTitle = RoutesTitles.about.title
            }
        }
    }
}
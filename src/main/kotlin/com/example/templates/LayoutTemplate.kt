package com.example.routes.web

import com.example.enums.RoutesTitles
import com.example.models.movieStorage
import io.ktor.server.html.*
import kotlinx.html.*

class LayoutTemplate: Template<HTML> {
    val menu = TemplatePlaceholder<Menu>()
    lateinit var content: String
    lateinit var sTitle: String
    override fun HTML.apply() {

        menu{
            active=content
        }

        head {
            title("Web pelis")
            link {
                rel = "stylesheet"
                href = "/static/css/style.css"
            }
            /*link {
                rel = "stylesheet"
                href = "https://www.w3schools.com/w3css/4/w3.css"
            }*/
            link {
                rel = "icon"
                type = "image/x-icon"
                href = "/static/images/logo.ico"
            }
        }
        body {
            img {
                src="/static/images/logo.png"
                classes= setOf("main_logo")
            }
            h1{
                +"Web pelis"
            }
            insert(Menu(), menu)
            h2 {
                text(sTitle)
            }
            when(content) {
                RoutesTitles.all.route -> insert(AllFilmsTemplate(), TemplatePlaceholder())
                RoutesTitles.about.route -> insert(AboutUsTemplate(), TemplatePlaceholder())
                RoutesTitles.new.route -> insert(NewTemplate(), TemplatePlaceholder())
                RoutesTitles.detail.route -> insert(DetailTemplate(), TemplatePlaceholder())
            }
            
        }
    }
}

class Menu: Template<FlowContent> {
    lateinit var active: String
    override fun FlowContent.apply() {

        ul {
            li {
                if(active == RoutesTitles.home.route) {
                    classes= setOf("active")
                }
                a {
                    href = "/home"
                    +"""Home"""
                }
            }
            li {
                if(active == RoutesTitles.all.route) {
                    classes= setOf("active")
                }
                a {
                    href = "/all"
                    +"""Llistat de pel.lícules"""
                }
            }
            li {
                if(active == RoutesTitles.new.route) {
                    classes= setOf("active")
                }
                a {
                    href = "/new"
                    +"""Nova peli"""
                }
            }
            li {
                if(active == RoutesTitles.about.route) {
                    classes= setOf("active")
                }
                style = "float:right"
                a {
                    href = "/about"
                    +"""About us"""
                }
            }

        }
    }
}

class AllFilmsTemplate: Template<FlowContent> {
    override fun FlowContent.apply() {
        val path = "/static/images/movies/"
        main("grid") {
            for (i in movieStorage) {

                article {
                    img {
                        src = path + i.image
                        alt = i.title
                        classes = setOf("movie_image")
                    }
                    div("text") {
                        h3 { +i.title }
                        p { +"""${i.gender} """ }
                        button {
                            type=ButtonType.button
                            onClick="location.href='/${RoutesTitles.detail}/${i.id.toString()}'"
                           +"Detall"
                        }
                    }
                }
            }
        }
    }
}

class AboutUsTemplate: Template<FlowContent> {
    override fun FlowContent.apply() {
        h4{
            +"Naïm Gómez"
        }
        h4{
            +"naim.gomez.7e5@itb.cat"
        }
    }
}

class NewTemplate: Template<FlowContent> {
    override fun FlowContent.apply() {
        form {
            action = "api/add"
            method = FormMethod.post
            encType = FormEncType.multipartFormData

            br(); label { +"Title" }
            input {
                type = InputType.text
                name = "title"
                id = "title"
            }

            br(); label { +"Year" }
            input {
                type = InputType.text
                name = "year"
                id = "year"
            }

            br(); label { +"Gender" }
            input {
                type = InputType.text
                name = "gender"
                id = "gender"
            }

            br(); label { +"Director" }
            input {
                type = InputType.text
                name = "director"
                id = "director"
            }

            br(); label { +"Image" }
            input {
                type = InputType.file
                name = "image"
                id = "image"
            }

            br { }
            input {
                type = InputType.submit
            }
        }
    }
}

class DetailTemplate: Template<FlowContent> {
    lateinit var moveId: String

    override fun FlowContent.apply() {
        h4{
            +"Naïm Gómez"
        }
        h4{
            +"naim.gomez.7e5@itb.cat"
        }
    }
}



package com.example.routes.api

import com.example.enums.RoutesEnum
import com.example.models.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Route.apiFilmsRouting() {
    route("api") {
        get("all") {
            if (movieStorage.isNotEmpty()) {
                call.respond(movieStorage)
            }
        }
        get("id/{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val customer =
                movieStorage.find { it.id == id } ?: return@get call.respondText(
                    "No movie with id $id",
                    status = HttpStatusCode.NotFound
                )
            call.respond(customer)
        }
        /*post("add") {
            val movie = call.receive<Movie>()

            val customer =
                movieStorage.find { it.id == movie.id }

            if(customer != null) {
                return@post call.respondText(
                    "Already exists a movie with that ID",
                    status = HttpStatusCode.BadRequest)
            }

            movieStorage.add(movie)
            call.respondText("Movie stored correctly", status = HttpStatusCode.Created)
        }*/
        post("add") {
            val datos = call.receiveMultipart()
            val oMovie = Movie("","","","","")

            datos.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        when (part.name){
                            "title" -> oMovie.title = part.value
                            "year" -> oMovie.year = part.value
                            "gender" -> oMovie.gender = part.value
                            "director" -> oMovie.director = part.value
                            "image" -> oMovie.image = part.value
                        }
                    }

                    //Segona part del when
                    is PartData.FileItem -> {
                        oMovie.image = (oMovie.title + "." + (part.originalFileName as String).substringAfterLast(".")).replace(" ", "_")
                        var fileBytes = part.streamProvider().readBytes()
                        File(File(".").getCanonicalPath()+"/build/resources/main"+moviesPath + oMovie.image).writeBytes(fileBytes)
                    }
                    else -> {
                        println("No se ha podido guardar la imagen")
                    }
                }}
            movieStorage.add(oMovie)
            println(movieStorage)

            call.respondRedirect("../${RoutesEnum.detail}/${oMovie.id.toString()}")

            call.respondText("Film stored correctly and \"${oMovie.image} is uploaded to '${moviesPath+oMovie.image}'\"", status = HttpStatusCode.Created)
        }

        post("id/add/{id?}") {
            val id = call.parameters["id"] ?: return@post call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest)

            movieStorage.find { it.id == id } ?: return@post call.respondText(
                "No movie with id $id",
                status = HttpStatusCode.NotFound)

            val comment = call.receive<Comment>()
            commentStorage.add(comment)
            call.respondText("Comment stored correctly", status = HttpStatusCode.Created)
        }

        post("update/id/{id?}") {
            val newMovie = call.receive<Movie>()

            val id = call.parameters["id"] ?: return@post call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            movieStorage.find { it.id == id } ?: return@post call.respondText(
                "No movie with id $id",
                status = HttpStatusCode.NotFound
            )

            movieStorage.removeIf { it.id == id }
            movieStorage.add(newMovie)
            call.respondText("Movie updated correctly", status = HttpStatusCode.Created)
        }

        delete("delete/id/{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            movieStorage.find { it.id == id } ?: return@delete call.respondText(
                "No movie with id $id",
                status = HttpStatusCode.NotFound
            )

            movieStorage.removeIf { it.id == id }
            call.respondText("Movie removed correctly", status = HttpStatusCode.Created)
        }

        get("id/comments/{id?}") {
            //
        }

        get("uploads/{imageName}") {
            val imageName = call.parameters["imageName"]
            var file = File("./uploads/$imageName")
            if(file.exists()){
                call.respondFile(File("./uploads/$imageName"))
            }
            else{
                call.respondText("Image not found", status = HttpStatusCode.NotFound)
            }
        }
    }
}

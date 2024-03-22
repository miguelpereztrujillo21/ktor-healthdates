package com.example.routes

import com.example.Database
import com.example.services.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting(){
    val connection = Database.getConnection()
    val userService = UserService(connection)
    route("users") {
        get {
            try {
                val users  = userService.getAllUsers()
                call.respond(HttpStatusCode.OK, users)
            }catch (e:Exception){
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}
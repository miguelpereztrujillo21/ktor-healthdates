package com.example.routes

import com.example.Database
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting(){
    val connection = Database.getConnection()
    route("users") {
        get {
            connection.createStatement().use { stamtement ->
                val query = "SELECT * from users"
                stamtement.executeQuery(query).use { result ->
                    while (result.next()) {
                        val name = result.getString("user_name")
                        call.respondText(name)
                    }
                }
            }
        }
    }
}
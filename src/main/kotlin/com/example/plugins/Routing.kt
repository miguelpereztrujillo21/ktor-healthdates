package com.example.plugins

import com.example.routes.appointmentRoutes
import com.example.routes.userRegisterRoute
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        appointmentRoutes()
        userRegisterRoute()
    }

}

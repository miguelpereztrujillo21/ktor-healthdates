package com.example.routes

import com.example.DatabaseConection
import com.example.domain.services.AppointmentService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.appointmentRoutes() {
    val appointmentService = AppointmentService(DatabaseConection.getConnection())
    route("appointments") {
        get("{id?}") {
            try {
                val id = call.parameters["id"]?: return@get call.respondText(
                    "id no encontrado",
                    status = HttpStatusCode.BadRequest
                )
                val appointments = appointmentService.getAppointmentById(id)
                call.respond(HttpStatusCode.OK, appointments)

            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound)

            }
        }
    }
}
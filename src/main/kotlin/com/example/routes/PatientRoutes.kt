package com.example.routes


import com.example.routes.models.RegisterPatientRequest
import com.example.domain.models.Patient
import com.example.domain.usecases.RegisterPatientUseCase
import com.example.domain.utils.constants.Routes
import com.example.domain.utils.constants.StatusCodeConstants
import com.example.routes.mappers.toDomain
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.toString

fun Route.patientRoutes(registerPatientUseCase: RegisterPatientUseCase) {
    post(Routes.PATIENTREGISTER) {
        try {
            val req = call.receive<RegisterPatientRequest>()
            val patient = req.toDomain()
            registerPatientUseCase.register(req.email, req.password, patient)
            call.respond(HttpStatusCode.Created, StatusCodeConstants.PATIENT_REGISTERED)
        } catch (e: IllegalArgumentException) {
            call.respond(HttpStatusCode.BadRequest, "Error: ${e.message}")
        }
    }
}
package com.example.routes


import com.example.routes.models.RegisterPatientRequest
import com.example.domain.usecases.LoginPatientUseCase
import com.example.domain.usecases.RegisterPatientUseCase
import com.example.domain.utils.constants.Routes
import com.example.domain.utils.constants.StatusCodeConstants
import com.example.routes.mappers.toDomain
import com.example.routes.models.LoginRequest
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.patientRoutes(registerPatientUseCase: RegisterPatientUseCase,
                        loginPatientUseCase: LoginPatientUseCase)  {
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
    post(Routes.PATIENTLOGIN) {
        try {
            val req = call.receive<LoginRequest>()
            val token = loginPatientUseCase.login(req.email, req.password)
            if (token != null) {
                call.respond(HttpStatusCode.OK, mapOf("token" to token))
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Credenciales inválidas")
            }
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, "Error: ${e.message}")
        }
    }
}

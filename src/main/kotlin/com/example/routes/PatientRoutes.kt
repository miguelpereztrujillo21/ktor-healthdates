package com.example.routes

import com.example.routes.models.RegisterPatientRequest
import com.example.domain.models.Patient
import com.example.domain.usecases.RegisterPatientUseCase
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.patientRoutes(registerPatientUseCase: RegisterPatientUseCase) {
    post("/register-patient") {
        val req = call.receive<RegisterPatientRequest>()
        val patient = Patient(
            userId = "", // Se asigna después de crear el usuario
            firstName = req.firstName,
            lastName = req.lastName,
            birthDate = req.birthDate,
            gender = req.gender,
            address = req.address,
            phone = req.phone
        )
        registerPatientUseCase.register(req.email, req.password, patient)
        call.respondText("Paciente registrado correctamente")
    }
}
package com.example.plugins

import com.example.di.DaggerAppComponent
import com.example.domain.usecases.RegisterPatientUseCase
import com.example.routes.appointmentRoutes
import com.example.routes.patientRoutes
import com.example.routes.userRegisterRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() { // Initialize Dagger component
    routing {
        appointmentRoutes()
        userRegisterRoute(DaggerAppComponent.create().userRepository())
        patientRoutes(RegisterPatientUseCase(
            DaggerAppComponent.create().userRepository(),
            DaggerAppComponent.create().patientRepository()))
    }

}

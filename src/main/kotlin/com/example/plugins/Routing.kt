package com.example.plugins

import com.example.di.DaggerAppComponent
import com.example.routes.patientRoutes
import com.example.routes.userRegisterRoute
import com.example.routes.selectionRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val appComponent = DaggerAppComponent.create()
    routing {
        userRegisterRoute(appComponent.userRepository())
        patientRoutes(
            registerPatientUseCase = appComponent.registerPatientUseCase(),
            loginPatientUseCase = appComponent.loginPatientUseCase(),
            getPatientAppointmentsUseCase = appComponent.getPatientAppointmentsUseCase(),
            bookAppointmentUseCase = appComponent.bookAppointmentUseCase()
        )
        selectionRoutes(
            getMedicalServicesUseCase = appComponent.getMedicalServicesUseCase(),
            getMedicalProceduresUseCase = appComponent.getMedicalProceduresUseCase(),
            getProceduresByServiceUseCase = appComponent.getProceduresByServiceUseCase(),
            getDoctorsByServiceUseCase = appComponent.getDoctorsByServiceUseCase(),
            getAvailableDoctorsUseCase = appComponent.getAvailableDoctorsUseCase()
        )
    }
}

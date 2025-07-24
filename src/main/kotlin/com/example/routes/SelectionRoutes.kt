package com.example.routes

import com.example.domain.usecases.*
import com.example.domain.utils.constants.Routes
import com.example.routes.mappers.toPresentationMedicalService
import com.example.routes.mappers.toPresentationMedicalProcedure
import com.example.routes.mappers.toPresentationDoctor
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.selectionRoutes(
    getMedicalServicesUseCase: IGetMedicalServicesUseCase,
    getMedicalProceduresUseCase: IGetMedicalProceduresUseCase,
    getProceduresByServiceUseCase: IGetProceduresByServiceUseCase,
    getDoctorsByServiceUseCase: IGetDoctorsByServiceUseCase,
    getAvailableDoctorsUseCase: IGetAvailableDoctorsUseCase
) {
    route(Routes.SELECTION) {

        // GET /selection/services - Obtener todos los servicios médicos
        get(Routes.SERVICES) {
            try {
                val services = getMedicalServicesUseCase.execute()
                val serviceResponses = services.map { it.toPresentationMedicalService() }
                call.respond(HttpStatusCode.OK, serviceResponses)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Error al obtener servicios: ${e.message}")
            }
        }

        // GET /selection/procedures - Obtener todos los procedimientos médicos
        get(Routes.PROCEDURES) {
            try {
                val procedures = getMedicalProceduresUseCase.execute()
                val procedureResponses = procedures.map { it.toPresentationMedicalProcedure() }
                call.respond(HttpStatusCode.OK, procedureResponses)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Error al obtener procedimientos: ${e.message}")
            }
        }

        // GET /selection/services/{serviceId}/procedures - Obtener procedimientos por servicio
        get(Routes.SERVICES_BY_ID_PROCEDURES) {
            try {
                val serviceId = call.parameters["serviceId"]?.toIntOrNull()
                if (serviceId == null) {
                    call.respond(HttpStatusCode.BadRequest, "ID de servicio inválido")
                    return@get
                }

                val procedures = getProceduresByServiceUseCase.execute(serviceId)
                val procedureResponses = procedures.map { it.toPresentationMedicalProcedure() }
                call.respond(HttpStatusCode.OK, procedureResponses)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Error al obtener procedimientos: ${e.message}")
            }
        }

        // GET /selection/services/{serviceId}/doctors - Obtener doctores por servicio
        get(Routes.SERVICES_BY_ID_DOCTORS) {
            try {
                val serviceId = call.parameters["serviceId"]?.toIntOrNull()
                if (serviceId == null) {
                    call.respond(HttpStatusCode.BadRequest, "ID de servicio inválido")
                    return@get
                }

                val doctors = getDoctorsByServiceUseCase.execute(serviceId)
                val doctorResponses = doctors.map { it.toPresentationDoctor() }
                call.respond(HttpStatusCode.OK, doctorResponses)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Error al obtener doctores: ${e.message}")
            }
        }

        // GET /selection/doctors/available - Obtener doctores disponibles con filtros
        get(Routes.DOCTORS_AVAILABLE) {
            try {
                val serviceId = call.request.queryParameters["serviceId"]?.toIntOrNull()
                val procedureId = call.request.queryParameters["procedureId"]?.toIntOrNull()
                val date = call.request.queryParameters["date"]

                val doctors = getAvailableDoctorsUseCase.execute(serviceId, procedureId, date)
                val doctorResponses = doctors.map { it.toPresentationDoctor() }
                call.respond(HttpStatusCode.OK, doctorResponses)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Error al obtener doctores disponibles: ${e.message}")
            }
        }
    }
}

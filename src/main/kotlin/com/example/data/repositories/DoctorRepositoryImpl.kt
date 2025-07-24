package com.example.data.repositories

import com.example.data.mappers.toDomainDoctor
import com.example.data.tables.*
import com.example.domain.models.Doctor
import com.example.domain.repositories.IDoctorRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DoctorRepositoryImpl @Inject constructor() : IDoctorRepository {

    override suspend fun getAllDoctors(): List<Doctor> = transaction {
        Doctors.selectAll().map { row ->
            row.toDomainDoctor()
        }
    }

    override suspend fun getDoctorById(id: String): Doctor? = transaction {
        Doctors.select { Doctors.id eq java.util.UUID.fromString(id) }
            .singleOrNull()?.let { row ->
                row.toDomainDoctor()
            }
    }

    override suspend fun getDoctorsByService(serviceId: Int): List<Doctor> = transaction {
        (Doctors innerJoin DoctorService)
            .select { DoctorService.serviceId eq serviceId }
            .map { row ->
                row.toDomainDoctor()
            }
    }

    override suspend fun getDoctorsByServiceAndProcedure(serviceId: Int, procedureId: Int): List<Doctor> = transaction {
        // Primero verificar que el servicio tiene el procedimiento solicitado
        val serviceHasProcedure = ServiceProcedure
            .select {
                (ServiceProcedure.serviceId eq serviceId) and
                (ServiceProcedure.procedureId eq procedureId)
            }
            .count() > 0

        if (!serviceHasProcedure) {
            // Si el servicio no tiene el procedimiento, retorna lista vacía
            return@transaction emptyList()
        }

        // Si el servicio tiene el procedimiento, obtener doctores del servicio
        (Doctors innerJoin DoctorService)
            .select {
                (DoctorService.serviceId eq serviceId) and (Doctors.isActive eq true)
            }
            .map { row ->
                row.toDomainDoctor()
            }
    }

    override suspend fun getAvailableDoctors(serviceId: Int?, procedureId: Int?, date: String?): List<Doctor> = transaction {
        println("DEBUG: getAvailableDoctors llamado con serviceId=$serviceId, procedureId=$procedureId, date=$date")

        if (serviceId == null) {
            // Sin filtros, retorna todos los doctores activos
            val doctors = Doctors.select { Doctors.isActive eq true }
                .map { row -> row.toDomainDoctor() }
            println("DEBUG: Sin filtros, encontrados ${doctors.size} doctores")
            return@transaction doctors
        } else {
            // Con filtro de servicio
            println("DEBUG: Consultando doctores para serviceId=$serviceId")

            val doctorsQuery = (Doctors innerJoin DoctorService)
                .select {
                    (DoctorService.serviceId eq serviceId) and (Doctors.isActive eq true)
                }

            println("DEBUG: Ejecutando consulta para doctores con serviceId=$serviceId")
            val doctors = doctorsQuery.map { row -> row.toDomainDoctor() }
            println("DEBUG: Encontrados ${doctors.size} doctores para serviceId=$serviceId")

            if (procedureId != null) {
                // Verificar que el servicio tiene el procedimiento solicitado
                println("DEBUG: Verificando si serviceId=$serviceId tiene procedureId=$procedureId")
                val serviceHasProcedure = ServiceProcedure
                    .select {
                        (ServiceProcedure.serviceId eq serviceId) and
                        (ServiceProcedure.procedureId eq procedureId)
                    }
                    .count() > 0

                println("DEBUG: ¿Servicio tiene procedimiento? $serviceHasProcedure")

                if (!serviceHasProcedure) {
                    // Si el servicio no tiene el procedimiento, retorna lista vacía
                    println("DEBUG: Servicio no tiene el procedimiento, retornando lista vacía")
                    return@transaction emptyList()
                }
            }

            // TODO: Agregar filtrado por fecha cuando se implemente la disponibilidad

            return@transaction doctors
        }
    }
}

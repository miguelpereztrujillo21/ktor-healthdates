package com.example.data.repositories

import com.example.data.mappers.toDomainAppointment
import com.example.data.tables.Appointments
import com.example.domain.models.Appointment
import com.example.domain.repositories.IAppointmentRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class AppointmentRepositoryImpl : IAppointmentRepository {

    override suspend fun getAppointmentsByPatientId(
        patientId: String,
        onlyUpcoming: Boolean
    ): List<Appointment> {
        return transaction {
            val query = Appointments.select { Appointments.patientId eq java.util.UUID.fromString(patientId) }

            val filteredQuery = if (onlyUpcoming) {
                query.andWhere { Appointments.appointmentDatetime greater LocalDateTime.now() }
            } else {
                query
            }

            filteredQuery.orderBy(Appointments.appointmentDatetime to SortOrder.ASC)
                .map { it.toDomainAppointment() }
        }
    }
}

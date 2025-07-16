package com.example.data.mappers

import com.example.data.tables.Appointments
import com.example.data.tables.Patients
import com.example.domain.models.Appointment
import com.example.domain.models.Patient
import org.jetbrains.exposed.sql.ResultRow
import java.time.LocalDateTime

fun ResultRow.toDomainAppointment(): Appointment = Appointment(
    id = this[Appointments.id].toString(),
    patientId = this[Appointments.patientId].toString(),
    doctorId = this[Appointments.doctorId].toString(),
    serviceId = this[Appointments.serviceId],
    procedureId = this[Appointments.procedureId],
    appointmentDatetime = this[Appointments.appointmentDatetime],
    durationMinutes = this[Appointments.durationMinutes],
    reason = this[Appointments.reason],
    status = this[Appointments.status],
    createdAt = this[Appointments.createdAt]
)

fun ResultRow.toDomainPatient(): Patient = Patient(
    id = this[Patients.id].toString(),
    firstName = this[Patients.firstName],
    lastName = this[Patients.lastName],
    birthDate = this[Patients.birthDate],
    gender = this[Patients.gender],
    address = this[Patients.address],
    phone = this[Patients.phone],
    createdAt = this[Patients.createdAt].toString()
)

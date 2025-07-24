package com.example.data.mappers

import com.example.domain.models.Appointment
import com.example.domain.models.Patient
import com.example.data.tables.Appointments
import com.example.data.tables.Patients
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.time.LocalDateTime
import java.util.UUID

fun Patient.toInsertPatientData(statement: InsertStatement<Number>) {
    this.userId?.let { statement[Patients.userId] = UUID.fromString(it) }
    statement[Patients.firstName] = this.firstName
    statement[Patients.lastName] = this.lastName
    this.secondLastName?.let { statement[Patients.secondLastName] = it }
    statement[Patients.birthDate] = this.birthDate
    statement[Patients.gender] = this.gender
    statement[Patients.address] = this.address
    this.cityId?.let { statement[Patients.cityId] = it }
    statement[Patients.phone] = this.phone
    this.mobilePhone?.let { statement[Patients.mobilePhone] = it }
    this.emergencyContactName?.let { statement[Patients.emergencyContactName] = it }
    this.emergencyContactPhone?.let { statement[Patients.emergencyContactPhone] = it }
    this.nationalId?.let { statement[Patients.nationalId] = it }
    this.socialSecurityNumber?.let { statement[Patients.socialSecurityNumber] = it }
    statement[Patients.createdAt] = LocalDateTime.now()
}

fun Appointment.toInsertAppointmentData(statement: InsertStatement<Number>) {
    statement[Appointments.patientId] = UUID.fromString(this.patientId)
    statement[Appointments.doctorId] = UUID.fromString(this.doctorId)
    this.serviceId?.let { statement[Appointments.serviceId] = it }
    this.procedureId?.let { statement[Appointments.procedureId] = it }
    statement[Appointments.appointmentDatetime] = this.appointmentDatetime
    statement[Appointments.durationMinutes] = this.durationMinutes
    statement[Appointments.reason] = this.reason
    statement[Appointments.status] = this.status
    statement[Appointments.createdAt] = this.createdAt
}

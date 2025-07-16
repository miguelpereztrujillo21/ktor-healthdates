package com.example.routes.mappers

import com.example.domain.models.Patient
import com.example.domain.models.Appointment
import com.example.routes.models.RegisterPatientRequest
import com.example.routes.models.AppointmentResponse
import com.example.routes.models.BookAppointmentRequest
import java.time.LocalDate
import java.time.LocalDateTime

fun RegisterPatientRequest.toDomain(): Patient = Patient(
    id = null,
    userId = null,
    firstName = this.firstName,
    lastName = this.lastName,
    birthDate = this.birthDate?.let { LocalDate.parse(it) },
    gender = this.gender,
    address = this.address,
    phone = this.phone,
    createdAt = LocalDateTime.now().toString()
)

fun Appointment.toResponse(): AppointmentResponse = AppointmentResponse(
    id = this.id,
    patientId = this.patientId,
    doctorId = this.doctorId,
    serviceId = this.serviceId,
    procedureId = this.procedureId,
    appointmentDatetime = this.appointmentDatetime.toString(),
    durationMinutes = this.durationMinutes,
    reason = this.reason,
    status = this.status,
    createdAt = this.createdAt.toString()
)

fun List<Appointment>.toResponseList(): List<AppointmentResponse> = this.map { it.toResponse() }

// Mapper de presentación a dominio para BookAppointment
fun BookAppointmentRequest.toDomain(): Appointment = Appointment(
    id = "", // Se generará en el caso de uso
    patientId = "", // Se asignará en el caso de uso
    doctorId = this.doctorId,
    serviceId = this.serviceId,
    procedureId = this.procedureId,
    appointmentDatetime = LocalDateTime.parse(this.appointmentDatetime),
    durationMinutes = this.durationMinutes,
    reason = this.reason,
    status = "pending", // Se asignará en el caso de uso
    createdAt = LocalDateTime.now() // Se actualizará en el caso de uso
)

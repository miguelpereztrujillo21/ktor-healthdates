package com.example.domain.models

import java.time.LocalDateTime

data class Appointment(
    val id: String,
    val patientId: String,
    val doctorId: String,
    val serviceId: Int?,
    val procedureId: Int?,
    val appointmentDatetime: LocalDateTime,
    val durationMinutes: Int,
    val reason: String?,
    val status: String,
    val createdAt: LocalDateTime
)


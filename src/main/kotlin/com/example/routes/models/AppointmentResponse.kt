package com.example.routes.models

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class AppointmentResponse(
    val id: String,
    val patientId: String,
    val doctorId: String,
    val serviceId: Int?,
    val procedureId: Int?,
    val appointmentDatetime: String,
    val durationMinutes: Int,
    val reason: String?,
    val status: String,
    val createdAt: String
)

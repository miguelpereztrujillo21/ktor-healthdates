package com.example.routes.models

import kotlinx.serialization.Serializable

@Serializable
data class BookAppointmentRequest(
    val doctorId: String,
    val serviceId: Int?,
    val procedureId: Int?,
    val appointmentDatetime: String, // ISO format: "2025-07-20T10:30:00"
    val durationMinutes: Int = 30,
    val reason: String?
)

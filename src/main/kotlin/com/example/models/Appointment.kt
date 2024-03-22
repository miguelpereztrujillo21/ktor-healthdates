package com.example.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.Date

@Serializable
data class Appointment(
    val appointmentId: Int,
    val appointmentDate: String,
    val appointmentStatus: String,
    val doctorName: String,
    val serviceName: String,
    val treatmentName: String,
    val centerName: String,
    val userName: String
)
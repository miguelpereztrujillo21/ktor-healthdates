package com.example.models_old

import kotlinx.serialization.Serializable

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
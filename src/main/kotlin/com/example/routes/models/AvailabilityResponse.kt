package com.example.routes.models

import kotlinx.serialization.Serializable

@Serializable
data class AvailabilityResponse(
    val date: String, // "2025-07-20"
    val timeSlots: List<TimeSlot>
)

@Serializable
data class TimeSlot(
    val time: String, // "10:30"
    val available: Boolean
)

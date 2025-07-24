package com.example.routes.models

import kotlinx.serialization.Serializable

@Serializable
data class DoctorResponse(
    val id: String,
    val firstName: String,
    val lastName: String,
    val secondLastName: String? = null,
    val licenseNumber: String,
    val collegeName: String? = null,
    val email: String,
    val phone: String?,
    val mobilePhone: String? = null,
    val specializationLevel: String? = null,
    val yearsExperience: Int = 0,
    val medicalCenterId: String? = null,
    val isActive: Boolean = true
)

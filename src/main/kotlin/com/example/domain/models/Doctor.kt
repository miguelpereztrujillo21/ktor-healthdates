package com.example.domain.models

data class Doctor(
    val id: String,
    val userId: String,
    val medicalCenterId: String? = null,
    val firstName: String,
    val lastName: String,
    val secondLastName: String? = null,
    val licenseNumber: String,
    val collegeName: String? = null,
    val email: String,
    val phone: String?,
    val mobilePhone: String? = null,
    val specializationLevel: String? = null, // resident, specialist, consultant, head_of_service
    val yearsExperience: Int = 0,
    val isActive: Boolean = true,
    val createdAt: String? = null
)

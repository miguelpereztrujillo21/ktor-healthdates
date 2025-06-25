package com.example.routes.models

import kotlinx.serialization.Serializable

@Serializable
data class RegisterPatientRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val birthDate: String?, // formato ISO yyyy-MM-dd
    val gender: String?,
    val address: String?,
    val phone: String?
)
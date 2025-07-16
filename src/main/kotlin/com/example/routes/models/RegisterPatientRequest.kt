package com.example.routes.models

import kotlinx.serialization.Serializable

@Serializable
data class  RegisterPatientRequest(
    val id : String? = null,
    val userId: String? = null,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val birthDate: String?,
    val gender: String?,
    val address: String?,
    val phone: String?,
    val createdAt: String? = null
)
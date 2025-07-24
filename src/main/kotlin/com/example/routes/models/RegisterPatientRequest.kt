package com.example.routes.models

import kotlinx.serialization.Serializable

@Serializable
data class RegisterPatientRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val secondLastName: String? = null,
    val birthDate: String? = null, // "1985-03-15"
    val gender: String? = null,
    val address: String? = null,
    val cityId: Int? = null,
    val phone: String? = null,
    val mobilePhone: String? = null,
    val emergencyContactName: String? = null,
    val emergencyContactPhone: String? = null,
    val nationalId: String? = null,
    val socialSecurityNumber: String? = null
)
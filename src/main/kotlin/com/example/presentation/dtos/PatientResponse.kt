package com.example.presentation.dtos

import kotlinx.serialization.Serializable

@Serializable
data class PatientResponse(
    val id: String? = null,
    val userId: String? = null,
    val firstName: String,
    val lastName: String,
    val secondLastName: String? = null,
    val birthDate: String? = null,
    val gender: String? = null,
    val address: String? = null,
    val cityId: Int? = null,
    val phone: String? = null,
    val mobilePhone: String? = null,
    val emergencyContactName: String? = null,
    val emergencyContactPhone: String? = null,
    val nationalId: String? = null,
    val socialSecurityNumber: String? = null,
    val createdAt: String? = null
)

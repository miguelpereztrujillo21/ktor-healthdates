package com.example.domain.models

import java.time.LocalDate

data class Patient(
    val id: String? = null,
    val userId: String? = null,
    val firstName: String,
    val lastName: String,
    val secondLastName: String? = null,
    val birthDate: LocalDate?,
    val gender: String?,
    val address: String?,
    val cityId: Int? = null,
    val phone: String?,
    val mobilePhone: String? = null,
    val emergencyContactName: String? = null,
    val emergencyContactPhone: String? = null,
    val nationalId: String? = null,
    val socialSecurityNumber: String? = null,
    val createdAt: String? = null
)
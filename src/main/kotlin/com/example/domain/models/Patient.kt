package com.example.domain.models

import java.time.LocalDate

data class Patient(
    val id: String? = null,
    val firstName: String,
    val lastName: String,
    val birthDate: LocalDate?,
    val gender: String?,
    val address: String?,
    val phone: String?,
    val createdAt: String
)
package com.example.domain.models

data class Patient(
    val userId: String,
    val firstName: String,
    val lastName: String,
    val birthDate: String?,
    val gender: String?,
    val address: String?,
    val phone: String?
)
package com.example.domain.models

data class User(
    val id: String? = null,
    val email: String,
    val hashedPassword: String,
    val role: String
)

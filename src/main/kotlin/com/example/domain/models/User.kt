package com.example.domain.models

data class User(
    val id: String? = null,
    val email: String,
    val hashPassword: String,
    val role: String,
    val createdAt: String
)

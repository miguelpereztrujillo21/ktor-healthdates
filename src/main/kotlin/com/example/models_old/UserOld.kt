package com.example.models_old

import kotlinx.serialization.Serializable

@Serializable
data class UserOld(
    val id: Int,
    val name: String,
    val email: String,
    val address: String) {
}
package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class User(val id: Int,val name: String, val email: String, val address: String) {
}
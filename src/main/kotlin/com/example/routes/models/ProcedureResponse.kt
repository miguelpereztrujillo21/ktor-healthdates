package com.example.routes.models

import kotlinx.serialization.Serializable

@Serializable
data class ProcedureResponse(
    val id: Int,
    val name: String,
    val description: String?
)

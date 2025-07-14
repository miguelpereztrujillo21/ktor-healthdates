package com.example.routes.mappers

import com.example.domain.models.Patient
import com.example.routes.models.RegisterPatientRequest
import java.time.LocalDate
import java.time.LocalDateTime

fun RegisterPatientRequest.toDomain(): Patient = Patient(
    firstName = this.firstName,
    lastName = this.lastName,
    birthDate = this.birthDate?.let { LocalDate.parse(it) },
    gender = this.gender,
    address = this.address,
    phone = this.phone,
    createdAt = LocalDateTime.now().toString()
)
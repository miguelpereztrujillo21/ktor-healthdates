package com.example.domain.repositories

import com.example.domain.models.Patient
import java.util.*

interface IPatientRepository {
    suspend fun insertPatient(patient: Patient)
    suspend fun getPatientByUserId(userId: UUID): Patient?
    suspend fun findByUserId(userId: String): Patient?
    suspend fun updatePatient(patient: Patient): Boolean
}
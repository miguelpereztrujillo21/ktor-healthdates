package com.example.domain.repositories

import com.example.domain.models.Patient

interface PatientRepository {
    suspend fun insertPatient(patient: Patient)
}
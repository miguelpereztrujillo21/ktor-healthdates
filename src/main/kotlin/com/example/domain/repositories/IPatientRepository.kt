package com.example.domain.repositories

import com.example.domain.models.Patient

interface IPatientRepository {
    suspend fun insertPatient(patient: Patient)
}
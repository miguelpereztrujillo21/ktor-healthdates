package com.example.data.repositories

import com.example.data.mappers.toInsertPatientData
import com.example.data.tables.Patients
import com.example.domain.models.Patient
import com.example.domain.repositories.IPatientRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class PatientRepositoryImpl : IPatientRepository {
    override suspend fun insertPatient(patient: Patient) {
        transaction {
            Patients.insert {
                patient.toInsertPatientData(it)
            }
        }
    }
}
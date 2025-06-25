package com.example.data.repositories

import com.example.data.tables.Patients
import com.example.domain.models.Patient
import com.example.domain.repositories.PatientRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class PatientRepositoryImpl : PatientRepository {
    override suspend fun insertPatient(patient: Patient) {
        transaction {
            Patients.insert {
                it[Patients.userId] = patient.userId
                it[Patients.firstName] = patient.firstName
                it[Patients.lastName] = patient.lastName
                it[Patients.birthDate] = patient.birthDate
                it[Patients.gender] = patient.gender
                it[Patients.address] = patient.address
                it[Patients.phone] = patient.phone
            }
        }
    }
}
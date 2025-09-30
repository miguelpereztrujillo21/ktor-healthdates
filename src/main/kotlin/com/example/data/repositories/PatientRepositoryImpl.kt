package com.example.data.repositories

import com.example.data.mappers.toDomainPatient
import com.example.data.mappers.toInsertPatientData
import com.example.data.tables.Patients
import com.example.domain.models.Patient
import com.example.domain.repositories.IPatientRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

class PatientRepositoryImpl : IPatientRepository {
    override suspend fun insertPatient(patient: Patient) {
        transaction {
            Patients.insert {
                patient.toInsertPatientData(it)
            }
        }
    }

    override suspend fun getPatientByUserId(userId: UUID): Patient? {
        return transaction {
            Patients.select { Patients.userId eq userId }
                .singleOrNull()
                ?.toDomainPatient()
        }
    }

    override suspend fun findByUserId(userId: String): Patient? {
        return try {
            val uuid = UUID.fromString(userId)
            getPatientByUserId(uuid)
        } catch (e: IllegalArgumentException) {
            null
        }
    }

    override suspend fun updatePatient(patient: Patient): Boolean {
        return transaction {
            if (patient.id == null) return@transaction false

            val patientUuid = try {
                UUID.fromString(patient.id)
            } catch (e: IllegalArgumentException) {
                return@transaction false
            }

            val updated = Patients.update({ Patients.id eq patientUuid }) {
                it[firstName] = patient.firstName
                it[lastName] = patient.lastName
                it[secondLastName] = patient.secondLastName
                it[birthDate] = patient.birthDate
                it[gender] = patient.gender
                it[address] = patient.address
                it[cityId] = patient.cityId
                it[phone] = patient.phone
                it[mobilePhone] = patient.mobilePhone
                it[emergencyContactName] = patient.emergencyContactName
                it[emergencyContactPhone] = patient.emergencyContactPhone
                it[nationalId] = patient.nationalId
                it[socialSecurityNumber] = patient.socialSecurityNumber
            }
            updated > 0
        }
    }
}
package com.example.data.mappers

import com.example.domain.models.Patient
import com.example.data.tables.Patients
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.time.LocalDateTime
import java.util.UUID

fun Patient.toInsertPatientData(statement: InsertStatement<Number>) {
    this.userId?.let { statement[Patients.userId] = UUID.fromString(it) }
    statement[Patients.firstName] = this.firstName
    statement[Patients.lastName] = this.lastName
    statement[Patients.birthDate] = this.birthDate
    statement[Patients.gender] = this.gender
    statement[Patients.address] = this.address
    statement[Patients.phone] = this.phone
    statement[Patients.createdAt] = LocalDateTime.now()
}
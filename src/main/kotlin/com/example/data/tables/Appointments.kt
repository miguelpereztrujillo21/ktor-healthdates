package com.example.data.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.util.UUID

object Appointments : Table() {
    val id = uuid("id").clientDefault { UUID.randomUUID() }
    val patientId = uuid("patient_id")
    val doctorId = uuid("doctor_id")
    val serviceId = integer("service_id").nullable()
    val procedureId = integer("procedure_id").nullable()
    val appointmentDatetime = datetime("appointment_datetime")
    val durationMinutes = integer("duration_minutes").default(30)
    val reason = text("reason").nullable()
    val status = varchar("status", 20).default("pending")
    val createdAt = datetime("created_at")
    override val primaryKey = PrimaryKey(id)
}


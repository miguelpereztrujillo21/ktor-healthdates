package com.example.data.tables

import org.jetbrains.exposed.sql.Table

object ServiceProcedure : Table("service_procedure") {
    val serviceId = integer("service_id").references(MedicalServices.id)
    val procedureId = integer("procedure_id").references(MedicalProcedures.id)
    override val primaryKey = PrimaryKey(serviceId, procedureId)
}

object DoctorService : Table("doctor_service") {
    val doctorId = uuid("doctor_id").references(Doctors.id)
    val serviceId = integer("service_id").references(MedicalServices.id)
    override val primaryKey = PrimaryKey(doctorId, serviceId)
}

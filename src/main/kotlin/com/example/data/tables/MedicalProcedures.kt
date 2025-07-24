package com.example.data.tables

import org.jetbrains.exposed.sql.Table

object MedicalProcedures : Table("medical_procedures") {
    val id = integer("id").autoIncrement()
    val name = text("name")
    val description = text("description").nullable()
    override val primaryKey = PrimaryKey(id)
}

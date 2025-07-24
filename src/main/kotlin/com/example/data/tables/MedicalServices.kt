package com.example.data.tables

import org.jetbrains.exposed.sql.Table

object MedicalServices : Table("medical_services") {
    val id = integer("id").autoIncrement()
    val name = text("name")
    val description = text("description").nullable()
    override val primaryKey = PrimaryKey(id)
}

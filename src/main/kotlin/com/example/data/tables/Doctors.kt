package com.example.data.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.util.UUID

object Doctors : Table("doctors") {
    val id = uuid("id").clientDefault { UUID.randomUUID() }
    val userId = uuid("user_id")
    val medicalCenterId = uuid("medical_center_id").nullable()
    val firstName = varchar("first_name", 50)
    val lastName = varchar("last_name", 50)
    val secondLastName = varchar("second_last_name", 50).nullable()
    val licenseNumber = varchar("license_number", 20)
    val collegeName = varchar("college_name", 100).nullable()
    val email = varchar("email", 100)
    val phone = varchar("phone", 20).nullable()
    val mobilePhone = varchar("mobile_phone", 20).nullable()
    val specializationLevel = varchar("specialization_level", 20).nullable()
    val yearsExperience = integer("years_experience").default(0)
    val isActive = bool("is_active").default(true)
    val createdAt = datetime("created_at")
    override val primaryKey = PrimaryKey(id)
}

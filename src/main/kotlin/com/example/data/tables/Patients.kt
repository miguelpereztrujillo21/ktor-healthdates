package com.example.data.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.datetime
import java.util.UUID

object Patients : Table("patients") {
    val id = uuid("id").clientDefault { UUID.randomUUID() }
    val userId = uuid("user_id").nullable()
    val firstName = varchar("first_name", 50)
    val lastName = varchar("last_name", 50)
    val secondLastName = varchar("second_last_name", 50).nullable()
    val birthDate = date("birth_date").nullable()
    val gender = varchar("gender", 10).nullable()
    val address = varchar("address", 255).nullable()
    val cityId = integer("city_id").nullable()
    val phone = varchar("phone", 20).nullable()
    val mobilePhone = varchar("mobile_phone", 20).nullable()
    val emergencyContactName = varchar("emergency_contact_name", 100).nullable()
    val emergencyContactPhone = varchar("emergency_contact_phone", 20).nullable()
    val nationalId = varchar("national_id", 20).nullable()
    val socialSecurityNumber = varchar("social_security_number", 20).nullable()
    val createdAt = datetime("created_at")
    override val primaryKey = PrimaryKey(id)
}
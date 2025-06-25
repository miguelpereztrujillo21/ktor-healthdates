package com.example.data.tables

import org.jetbrains.exposed.sql.Table

object Patients : Table() {
    val userId = varchar("user_id", 36)
    val firstName = varchar("first_name", 50)
    val lastName = varchar("last_name", 50)
    val birthDate = varchar("birth_date", 10).nullable()
    val gender = varchar("gender", 10).nullable()
    val address = varchar("address", 100).nullable()
    val phone = varchar("phone", 20).nullable()
    override val primaryKey = PrimaryKey(userId)
}
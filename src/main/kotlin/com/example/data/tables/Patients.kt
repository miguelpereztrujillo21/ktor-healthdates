package com.example.data.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import org.jetbrains.exposed.sql.javatime.datetime
import java.util.UUID

object Patients : Table() {
    val id = uuid("id").clientDefault { UUID.randomUUID() }
    val user_id = uuid("user_id").nullable()
    val firstName = varchar("first_name", 50)
    val lastName = varchar("last_name", 50)
    val birthDate = date("birth_date").nullable()
    val gender = varchar("gender", 10).nullable()
    val address = varchar("address", 100).nullable()
    val phone = varchar("phone", 20).nullable()
    val createdAt = datetime("created_at")
    override val primaryKey = PrimaryKey(id)
}
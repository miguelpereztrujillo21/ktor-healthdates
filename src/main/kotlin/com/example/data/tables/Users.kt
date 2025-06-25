package com.example.data.tables

import org.jetbrains.exposed.sql.Table
import java.util.UUID
import org.jetbrains.exposed.sql.javatime.datetime

object Users : Table() {
    val id = uuid("id").clientDefault { UUID.randomUUID() }
    val email = varchar("email", 255)
    val hashPassword = varchar("hash_password", 255)
    val role = varchar("role", 50)
    val createdAt = datetime("created_at")
}
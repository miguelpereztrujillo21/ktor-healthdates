package com.example

import java.sql.Connection
import java.sql.DriverManager

object DatabaseConection {
    @JvmStatic
    fun getConnection() : Connection {
        val connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/medical_db",
            "admin",
            "adminpassword")
        return connection
    }
}
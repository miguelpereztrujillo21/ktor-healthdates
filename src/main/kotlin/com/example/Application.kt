package com.example

import com.example.config.AppConfig
import com.example.data.tables.*
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    embeddedServer(Netty, port = AppConfig.serverPort, host = AppConfig.serverHost, module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    Database.connect(
        url = AppConfig.databaseUrl,
        driver = AppConfig.databaseDriver,
        user = AppConfig.databaseUser,
        password = AppConfig.databasePassword
    )

    transaction {
        SchemaUtils.create(
            Users, Patients, Appointments,
            MedicalServices, MedicalProcedures, Doctors,
            ServiceProcedure, DoctorService
        )
    }

    configureSerialization()
    configureRouting()
}

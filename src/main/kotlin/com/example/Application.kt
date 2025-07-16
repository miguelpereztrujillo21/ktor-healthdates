package com.example

import com.example.di.DaggerAppComponent
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1", module = Application::module)
        .start(wait = true)
    val appComponent = DaggerAppComponent.create()
    val userRepository = appComponent.userRepository()
}

fun Application.module() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/medical_db",
        driver = "org.postgresql.Driver",
        user = "admin",
        password = "adminpassword"
    )
    configureSerialization()
    configureRouting()
}

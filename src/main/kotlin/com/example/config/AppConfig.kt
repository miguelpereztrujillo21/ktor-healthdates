package com.example.config

import java.util.Properties

object AppConfig {
    private val properties = Properties()

    init {
        val inputStream = AppConfig::class.java.classLoader.getResourceAsStream("application.properties")
        properties.load(inputStream)
    }

    // Database Configuration
    val databaseUrl: String get() = properties.getProperty("database.url")
    val databaseDriver: String get() = properties.getProperty("database.driver")
    val databaseUser: String get() = properties.getProperty("database.user")
    val databasePassword: String get() = properties.getProperty("database.password")

    // Server Configuration
    val serverHost: String get() = properties.getProperty("server.host")
    val serverPort: Int get() = properties.getProperty("server.port").toInt()

    // JWT Configuration
    val jwtSecret: String get() = properties.getProperty("jwt.secret")
    val jwtExpirationMillis: Long get() = properties.getProperty("jwt.expiration.millis").toLong()

    // Validation Configuration
    val passwordMinLength: Int get() = properties.getProperty("password.min.length").toInt()
}

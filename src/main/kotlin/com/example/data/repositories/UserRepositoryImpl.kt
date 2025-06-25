package com.example.data.repositories

import com.example.data.tables.Users
import com.example.domain.models.User
import com.example.domain.repositories.IUserRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime


class UserRepositoryImpl : IUserRepository {
    override suspend fun create(user: User): String {
        return transaction {
            val inserted = Users.insert {
                it[Users.email] = user.email
                it[Users.hashPassword] = user.hashPassword
                it[Users.role] = user.role
                it[Users.createdAt] = LocalDateTime.now()
            }
            inserted[Users.id]?.toString() ?: throw Exception("No se pudo obtener el id")
        }
    }

    override suspend fun findByEmail(email: String): User? {
        return transaction {
            Users.select { Users.email eq email }
                .map {
                    User(
                        id = it[Users.id].toString(),
                        email = it[Users.email],
                        hashPassword = it[Users.hashPassword],
                        role = it[Users.role],
                        createdAt = it[Users.createdAt].toString()
                    )
                }
                .singleOrNull()
        }
    }
}
package com.example.domain.repositories

import com.example.domain.models.User

interface IUserRepository {
    suspend fun create(user: User): String
    suspend fun findByEmail(email: String): User?
}
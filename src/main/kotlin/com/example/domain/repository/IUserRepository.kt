package com.example.domain.repository

import com.example.domain.models.User

ç
interface IUserRepository {
    suspend fun create(user: User): Boolean
    suspend fun findByEmail(email: String): User?
}
package com.example.domain.usecases

import com.example.domain.repositories.IUserRepository
import com.example.domain.services.JwtTokenProvider
import org.mindrot.jbcrypt.BCrypt

class LoginPatientUseCase(
    private val userRepository: IUserRepository,
    private val jwtTokenProvider: JwtTokenProvider = JwtTokenProvider()
) : ILoginPatientUseCase {
    override suspend fun login(email: String, password: String): String? {
        val user = userRepository.findByEmail(email)
        return if (user != null && BCrypt.checkpw(password, user.hashPassword)) {
            val userId = user.id ?: throw IllegalArgumentException("Usuario sin ID válido")
            jwtTokenProvider.generateToken(
                java.util.UUID.fromString(userId),
                user.email,
                user.role,
                3600000L
            )
        } else {
            null
        }
    }
}
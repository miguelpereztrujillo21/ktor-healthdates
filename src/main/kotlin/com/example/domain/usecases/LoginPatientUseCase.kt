package com.example.domain.usecases

import com.example.domain.repositories.IUserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.mindrot.jbcrypt.BCrypt
import java.util.Base64
import java.util.Date
import javax.crypto.SecretKey

class LoginPatientUseCase(
    private val userRepository: IUserRepository
) : ILoginPatientUseCase {
    override suspend fun login(email: String, password: String): String? {
        val user = userRepository.findByEmail(email)
        return if (user != null && BCrypt.checkpw(password, user.hashPassword)) {
            val now = System.currentTimeMillis()
            val exp = now + 3600000 // 1 hora
            val secret = System.getenv("JWT_SECRET") ?: "V4X9N1GpQzFvZDhKRWpTeUJQYnVmVlpvTmRxSmw3dUtZbTRKcEZLeHdLMHRaM0Nx"
            val decodedSecret = Base64.getDecoder().decode(secret)
            require(decodedSecret.size >= 32) { "La clave secreta debe tener al menos 256 bits (32 bytes)." }
            val secretKey: SecretKey = Keys.hmacShaKeyFor(decodedSecret)
            Jwts.builder()
                .setSubject(email)
                .setExpiration(Date(exp))
                .claim("role", "web_anon")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact()
        } else {
            null
        }
    }
}
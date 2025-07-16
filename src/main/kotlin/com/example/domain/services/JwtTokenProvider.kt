package com.example.domain.services

import com.example.config.AppConfig
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.util.Base64
import java.util.Date
import javax.crypto.SecretKey

class JwtTokenProvider {
    private val secret: String = AppConfig.jwtSecret
    private val decodedSecret: ByteArray = Base64.getDecoder().decode(secret)
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(decodedSecret)

    init {
        require(decodedSecret.size >= 32) { "La clave secreta debe tener al menos 256 bits (32 bytes)." }
    }

    fun generateToken(email: String, role: String = "web_anon", expirationMillis: Long = AppConfig.jwtExpirationMillis): String {
        val now = System.currentTimeMillis()
        val exp = now + expirationMillis
        return Jwts.builder()
            .setSubject(email)
            .setExpiration(Date(exp))
            .claim("role", role)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun extractEmailFromToken(token: String): String? {
        return try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
            claims.body.subject
        } catch (e: Exception) {
            null
        }
    }
}

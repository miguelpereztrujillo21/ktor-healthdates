package com.example.domain.services

import com.example.config.AppConfig
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.util.Base64
import java.util.Date
import java.util.UUID
import javax.crypto.SecretKey

class JwtTokenProvider {
    private val secret: String = AppConfig.jwtSecret
    private val decodedSecret: ByteArray = Base64.getDecoder().decode(secret)
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(decodedSecret)

    init {
        require(decodedSecret.size >= 32) { "La clave secreta debe tener al menos 256 bits (32 bytes)." }
    }

    // sub = UUID (como String). Se añade el correo en el claim "email".
    fun generateToken(userId: UUID, email: String, role: String = "web_anon", expirationMillis: Long = AppConfig.jwtExpirationMillis): String {
        val now = System.currentTimeMillis()
        val exp = now + expirationMillis
        return Jwts.builder()
            .setSubject(userId.toString())
            .setExpiration(Date(exp))
            .setIssuedAt(Date(now))
            .claim("email", email)
            .claim("role", role)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun extractUserIdFromToken(token: String): UUID? =
        try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .body
            UUID.fromString(claims.subject)
        } catch (_: Exception) {
            null
        }

    fun extractEmailFromToken(token: String): String? =
        try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .body
            (claims["email"] as? String) ?: claims.subject // respaldo si aún usas sub=email
        } catch (_: Exception) {
            null
        }
}
package com.example.routes.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import java.nio.charset.StandardCharsets
import java.util.UUID
import javax.crypto.SecretKey

private const val TAG = "[TokenUtils]"

private fun mask(value: String?): String =
    when {
        value.isNullOrBlank() -> "<vacío>"
        value.length <= 12 -> "****"
        else -> value.take(6) + "..." + value.takeLast(6)
    }

private fun signingKey(): SecretKey {
    val fromEnv = System.getenv("JWT_SECRET")
    val usingEnv = fromEnv != null
    val secret = fromEnv ?: "solo-para-dev-cambia-esta-clave-min-32-bytes"

    println("$TAG secreto: origen=${if (usingEnv) "env:JWT_SECRET" else "default"}, len=${secret.length}")

    val decoded = runCatching { Decoders.BASE64.decode(secret) }.getOrNull()
    val key = if (decoded != null) {
        println("$TAG secreto: modo=Base64, decodedLen=${decoded.size}")
        Keys.hmacShaKeyFor(decoded)
    } else {
        println("$TAG secreto: modo=texto, bytesLen=${secret.toByteArray(StandardCharsets.UTF_8).size}")
        Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
    }

    val keyBytes = key.encoded
    println("$TAG clave generada: alg=${key.algorithm}, keyBits=${keyBytes?.size?.times(8) ?: -1}")
    return key
}

fun extractUserIdFromToken(token: String): UUID? {
    println("$TAG token recibido: ${mask(token)}")
    return try {
        val secretKey: SecretKey = signingKey()

        val cleanToken = token.removePrefix("Bearer ").trim()
        println("$TAG token limpio: ${mask(cleanToken)}")

        val jws = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(cleanToken)

        val claims = jws.body
        println("$TAG claims: sub=${claims.subject}, exp=${claims.expiration}, role=${claims["role"]}, user_id=${claims["user_id"]}")

        val userIdOrSub = (claims["user_id"] as? String) ?: claims.subject
        println("$TAG id extraído (user_id|sub): ${mask(userIdOrSub)}")

        val uuid = runCatching { UUID.fromString(userIdOrSub) }.onFailure {
            println("$TAG el id no es UUID válido: ${it.javaClass.simpleName}: ${it.message}")
        }.getOrNull()

        println("$TAG resultado UUID: ${uuid != null}")
        uuid
    } catch (e: Exception) {
        println("$TAG error al validar/parsing JWT: ${e.javaClass.simpleName}: ${e.message}")
        e.printStackTrace()
        null
    }
}

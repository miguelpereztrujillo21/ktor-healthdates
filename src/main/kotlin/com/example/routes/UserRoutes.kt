package com.example.routes

import com.example.data.repositories.UserRepositoryImpl
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.example.domain.models.User
import com.example.domain.utils.constants.Routes

import com.example.domain.utils.constants.UserParams
import com.example.domain.utils.constants.UserRolesEnum
import io.ktor.server.request.*

import java.time.LocalDateTime
fun Route.userRegisterRoute() {
    val userRepository = UserRepositoryImpl()
    post(Routes.REGISTER) {
        val params = call.receiveParameters()
        val email = params[UserParams.EMAIL] ?: return@post call.respondText("Falta email", status = io.ktor.http.HttpStatusCode.BadRequest)
        val password = params[UserParams.PASSWORD] ?: return@post call.respondText("Falta password", status = io.ktor.http.HttpStatusCode.BadRequest)
        val role = params[UserParams.ROLE] ?: UserRolesEnum.PATIENT.value
        val user = User(
            id = null,
            email = email,
            hashPassword = password,
            role = role,
            createdAt = LocalDateTime.now().toString()
        )
        val userId = userRepository.create(user)
        if (userId != null) {
            call.respondText("Usuario registrado con id: $userId")
        } else {
            call.respondText("Error al registrar usuario", status = io.ktor.http.HttpStatusCode.InternalServerError)
        }
    }
}
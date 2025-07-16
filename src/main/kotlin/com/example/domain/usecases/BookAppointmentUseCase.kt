package com.example.domain.usecases

import com.example.domain.models.Appointment
import com.example.domain.repositories.IAppointmentRepository
import com.example.domain.repositories.IPatientRepository
import com.example.domain.repositories.IUserRepository
import com.example.domain.services.JwtTokenProvider
import java.time.LocalDateTime
import java.util.UUID

class BookAppointmentUseCase(
    private val userRepository: IUserRepository,
    private val patientRepository: IPatientRepository,
    private val appointmentRepository: IAppointmentRepository,
    private val jwtTokenProvider: JwtTokenProvider = JwtTokenProvider()
) : IBookAppointmentUseCase {

    override suspend fun bookAppointment(
        token: String,
        appointment: Appointment
    ): Appointment {
        // Extraer email del token
        val email = jwtTokenProvider.extractEmailFromToken(token)
            ?: throw IllegalArgumentException("Token inválido")

        // Buscar usuario por email
        val user = userRepository.findByEmail(email)
            ?: throw IllegalArgumentException("Usuario no encontrado")

        // Verificar que el usuario tenga id
        val userId = user.id ?: throw IllegalArgumentException("Usuario sin ID válido")

        // Buscar paciente por user_id
        val patient = patientRepository.findByUserId(userId)
            ?: throw IllegalArgumentException("Paciente no encontrado")

        // Verificar que el paciente tenga id
        val patientId = patient.id ?: throw IllegalArgumentException("Paciente sin ID válido")

        // Crear la nueva cita con el patientId correcto y datos actualizados
        val appointmentToSave = appointment.copy(
            id = UUID.randomUUID().toString(),
            patientId = patientId,
            status = "pending",
            createdAt = LocalDateTime.now()
        )

        // Guardar la cita y retornarla
        return appointmentRepository.createAppointment(appointmentToSave)
    }
}
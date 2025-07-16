package com.example.domain.usecases

import com.example.domain.models.Appointment
import com.example.domain.repositories.IAppointmentRepository
import com.example.domain.repositories.IPatientRepository
import com.example.domain.repositories.IUserRepository
import com.example.domain.services.JwtTokenProvider

class GetPatientAppointmentsUseCase(
    private val userRepository: IUserRepository,
    private val patientRepository: IPatientRepository,
    private val appointmentRepository: IAppointmentRepository,
    private val jwtTokenProvider: JwtTokenProvider = JwtTokenProvider()
) : IGetPatientAppointmentsUseCase {

    override suspend fun getAppointments(token: String, onlyUpcoming: Boolean): List<Appointment> {
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

        // Obtener citas del paciente
        return appointmentRepository.getAppointmentsByPatientId(patientId, onlyUpcoming)
    }
}

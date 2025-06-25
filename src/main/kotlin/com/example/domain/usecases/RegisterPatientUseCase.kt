package com.example.domain.usecases

import com.example.domain.models.Patient
import com.example.domain.models.User
import com.example.domain.repositories.IUserRepository
import com.example.domain.repositories.PatientRepository
import org.mindrot.jbcrypt.BCrypt
import java.time.LocalDateTime

class RegisterPatientUseCase(
    private val userRepository: IUserRepository,
    private val patientRepository: PatientRepository
) {
    suspend fun register(email: String, password: String, patient: Patient) {
        require(email.isNotBlank())
        require(password.length >= 8)
        require(patient.firstName.isNotBlank())
        require(patient.lastName.isNotBlank())

        val hash = BCrypt.hashpw(password, BCrypt.gensalt())
        val user = User(
            id = null,
            email = email,
            hashPassword = hash,
            role = "patient",
            createdAt = LocalDateTime.now().toString()
        )
        val userId = userRepository.create(user)
        val patientWithUserId = patient.copy(userId = userId)
        patientRepository.insertPatient(patientWithUserId)
    }
}
package com.example.domain.usecases

import com.example.domain.models.Patient
import com.example.domain.models.User
import com.example.domain.repositories.IPatientRepository
import com.example.domain.repositories.IUserRepository
import com.example.domain.utils.constants.ErrorConstants
import com.example.domain.utils.constants.RegexConstants
import com.example.domain.utils.constants.UserRolesEnum
import org.mindrot.jbcrypt.BCrypt
import java.time.LocalDateTime

class RegisterPatientUseCase(
    private val userRepository: IUserRepository,
    private val patientRepository: IPatientRepository
) {
    suspend fun register(email: String, password: String, patient: Patient) {
        require(email.isNotBlank()) { ErrorConstants.ERROR_EMPTY_EMAIL }
        require(password.length >= 8) { ErrorConstants.ERROR_PASSWORD_TOO_SHORT }
        require(patient.firstName.isNotBlank()) { ErrorConstants.ERROR_EMPTY_FIRST_NAME }
        require(patient.lastName.isNotBlank()) { ErrorConstants.ERROR_EMPTY_LAST_NAME }
        require(RegexConstants.EMAIL_REGEX.matches(email)){ ErrorConstants.ERROR_INVALID_EMAIL_FORMAT }

        val hash = BCrypt.hashpw(password, BCrypt.gensalt())
        val user = User(
            id = null,
            email = email,
            hashPassword = hash,
            role = UserRolesEnum.PATIENT.value,
            createdAt = LocalDateTime.now().toString()
        )
        val userId = userRepository.create(user)
        val patientWithUserId = patient.copy(id = userId)
        patientRepository.insertPatient(patientWithUserId)
    }
}
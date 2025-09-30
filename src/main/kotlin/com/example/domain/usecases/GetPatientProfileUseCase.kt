package com.example.domain.usecases

import com.example.domain.models.Patient
import com.example.domain.repositories.IPatientRepository
import java.util.*
import javax.inject.Inject

class GetPatientProfileUseCase @Inject constructor(
    private val patientRepository: IPatientRepository
) : IGetPatientProfileUseCase {

    override suspend fun getProfile(userId: UUID): Patient? {
        return patientRepository.getPatientByUserId(userId)
    }
}

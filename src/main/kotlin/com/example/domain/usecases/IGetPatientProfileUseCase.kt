package com.example.domain.usecases

import com.example.domain.models.Patient
import java.util.*

interface IGetPatientProfileUseCase {
    suspend fun getProfile(userId: UUID): Patient?
}

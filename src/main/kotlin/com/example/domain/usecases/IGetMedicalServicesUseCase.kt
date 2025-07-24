package com.example.domain.usecases

import com.example.domain.models.MedicalService

interface IGetMedicalServicesUseCase {
    suspend fun execute(): List<MedicalService>
}

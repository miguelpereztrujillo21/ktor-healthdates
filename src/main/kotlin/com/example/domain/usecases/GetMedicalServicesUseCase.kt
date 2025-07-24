package com.example.domain.usecases

import com.example.domain.models.MedicalService
import com.example.domain.repositories.IMedicalServiceRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMedicalServicesUseCase @Inject constructor(
    private val medicalServiceRepository: IMedicalServiceRepository
) : IGetMedicalServicesUseCase {

    override suspend fun execute(): List<MedicalService> {
        return medicalServiceRepository.getAllServices()
    }
}

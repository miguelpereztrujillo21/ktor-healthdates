package com.example.domain.usecases

import com.example.domain.models.MedicalProcedure
import com.example.domain.repositories.IMedicalProcedureRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetProceduresByServiceUseCase @Inject constructor(
    private val medicalProcedureRepository: IMedicalProcedureRepository
) : IGetProceduresByServiceUseCase {

    override suspend fun execute(serviceId: Int): List<MedicalProcedure> {
        return medicalProcedureRepository.getProceduresByService(serviceId)
    }
}

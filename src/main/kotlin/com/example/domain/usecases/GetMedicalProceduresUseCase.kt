package com.example.domain.usecases

import com.example.domain.models.MedicalProcedure
import com.example.domain.repositories.IMedicalProcedureRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMedicalProceduresUseCase @Inject constructor(
    private val medicalProcedureRepository: IMedicalProcedureRepository
) : IGetMedicalProceduresUseCase {

    override suspend fun execute(): List<MedicalProcedure> {
        return medicalProcedureRepository.getAllProcedures()
    }
}

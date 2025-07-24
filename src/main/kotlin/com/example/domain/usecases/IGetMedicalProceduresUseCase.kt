package com.example.domain.usecases

import com.example.domain.models.MedicalProcedure

interface IGetMedicalProceduresUseCase {
    suspend fun execute(): List<MedicalProcedure>
}

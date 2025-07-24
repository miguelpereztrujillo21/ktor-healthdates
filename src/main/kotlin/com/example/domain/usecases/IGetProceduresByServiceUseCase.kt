package com.example.domain.usecases

import com.example.domain.models.MedicalProcedure

interface IGetProceduresByServiceUseCase {
    suspend fun execute(serviceId: Int): List<MedicalProcedure>
}

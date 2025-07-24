package com.example.domain.repositories

import com.example.domain.models.MedicalProcedure

interface IMedicalProcedureRepository {
    suspend fun getAllProcedures(): List<MedicalProcedure>
    suspend fun getProcedureById(id: Int): MedicalProcedure?
    suspend fun getProceduresByService(serviceId: Int): List<MedicalProcedure>
}

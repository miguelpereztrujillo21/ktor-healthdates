package com.example.domain.repositories

import com.example.domain.models.MedicalService

interface IMedicalServiceRepository {
    suspend fun getAllServices(): List<MedicalService>
    suspend fun getServiceById(id: Int): MedicalService?
}

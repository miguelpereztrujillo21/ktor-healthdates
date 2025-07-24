package com.example.data.repositories

import com.example.data.mappers.toDomainMedicalService
import com.example.data.tables.*
import com.example.domain.models.MedicalService
import com.example.domain.repositories.IMedicalServiceRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MedicalServiceRepositoryImpl @Inject constructor() : IMedicalServiceRepository {

    override suspend fun getAllServices(): List<MedicalService> = transaction {
        MedicalServices.selectAll().map { row ->
            row.toDomainMedicalService()
        }
    }

    override suspend fun getServiceById(id: Int): MedicalService? = transaction {
        MedicalServices.select { MedicalServices.id eq id }
            .singleOrNull()?.let { row ->
                row.toDomainMedicalService()
            }
    }
}

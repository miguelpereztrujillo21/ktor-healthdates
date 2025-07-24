package com.example.data.repositories

import com.example.data.mappers.toDomainMedicalProcedure
import com.example.data.tables.*
import com.example.domain.models.MedicalProcedure
import com.example.domain.repositories.IMedicalProcedureRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MedicalProcedureRepositoryImpl @Inject constructor() : IMedicalProcedureRepository {

    override suspend fun getAllProcedures(): List<MedicalProcedure> = transaction {
        MedicalProcedures.selectAll().map { row ->
            row.toDomainMedicalProcedure()
        }
    }

    override suspend fun getProcedureById(id: Int): MedicalProcedure? = transaction {
        MedicalProcedures.select { MedicalProcedures.id eq id }
            .singleOrNull()?.let { row ->
                row.toDomainMedicalProcedure()
            }
    }

    override suspend fun getProceduresByService(serviceId: Int): List<MedicalProcedure> = transaction {
        (MedicalProcedures innerJoin ServiceProcedure)
            .select { ServiceProcedure.serviceId eq serviceId }
            .map { row ->
                row.toDomainMedicalProcedure()
            }
    }
}

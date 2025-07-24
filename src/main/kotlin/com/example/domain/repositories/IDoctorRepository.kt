package com.example.domain.repositories

import com.example.domain.models.Doctor

interface IDoctorRepository {
    suspend fun getAllDoctors(): List<Doctor>
    suspend fun getDoctorById(id: String): Doctor?
    suspend fun getDoctorsByService(serviceId: Int): List<Doctor>
    suspend fun getDoctorsByServiceAndProcedure(serviceId: Int, procedureId: Int): List<Doctor>
    suspend fun getAvailableDoctors(serviceId: Int?, procedureId: Int?, date: String?): List<Doctor>
}

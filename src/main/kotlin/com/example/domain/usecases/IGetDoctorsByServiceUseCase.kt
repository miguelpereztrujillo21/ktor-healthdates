package com.example.domain.usecases

import com.example.domain.models.Doctor

interface IGetDoctorsByServiceUseCase {
    suspend fun execute(serviceId: Int): List<Doctor>
}

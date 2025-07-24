package com.example.domain.usecases

import com.example.domain.models.Doctor

interface IGetAvailableDoctorsUseCase {
    suspend fun execute(serviceId: Int?, procedureId: Int?, date: String?): List<Doctor>
}

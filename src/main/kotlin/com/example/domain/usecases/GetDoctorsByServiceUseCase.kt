package com.example.domain.usecases

import com.example.domain.models.Doctor
import com.example.domain.repositories.IDoctorRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDoctorsByServiceUseCase @Inject constructor(
    private val doctorRepository: IDoctorRepository
) : IGetDoctorsByServiceUseCase {

    override suspend fun execute(serviceId: Int): List<Doctor> {
        return doctorRepository.getDoctorsByService(serviceId)
    }
}

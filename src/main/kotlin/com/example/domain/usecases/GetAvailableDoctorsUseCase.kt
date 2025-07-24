package com.example.domain.usecases

import com.example.domain.models.Doctor
import com.example.domain.repositories.IDoctorRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAvailableDoctorsUseCase @Inject constructor(
    private val doctorRepository: IDoctorRepository
) : IGetAvailableDoctorsUseCase {
    override suspend fun execute(serviceId: Int?, procedureId: Int?, date: String?): List<Doctor> {
        return doctorRepository.getAvailableDoctors(serviceId, procedureId, date)
    }
}

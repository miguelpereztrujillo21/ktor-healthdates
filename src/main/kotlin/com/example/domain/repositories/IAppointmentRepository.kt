package com.example.domain.repositories

import com.example.domain.models.Appointment

interface IAppointmentRepository {
    suspend fun getAppointmentsByPatientId(patientId: String, onlyUpcoming: Boolean = false): List<Appointment>
}

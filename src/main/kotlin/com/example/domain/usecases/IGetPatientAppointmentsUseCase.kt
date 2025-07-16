package com.example.domain.usecases

import com.example.domain.models.Appointment

interface IGetPatientAppointmentsUseCase {
    suspend fun getAppointments(token: String, onlyUpcoming: Boolean = false): List<Appointment>
}

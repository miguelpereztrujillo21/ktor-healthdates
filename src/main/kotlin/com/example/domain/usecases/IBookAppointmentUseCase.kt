package com.example.domain.usecases

import com.example.domain.models.Appointment

interface IBookAppointmentUseCase {
    suspend fun bookAppointment(token: String, appointment: Appointment): Appointment
}

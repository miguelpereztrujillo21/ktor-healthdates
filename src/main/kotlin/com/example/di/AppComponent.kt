package com.example.di

import com.example.domain.repositories.IUserRepository
import com.example.domain.repositories.IPatientRepository
import com.example.domain.repositories.IAppointmentRepository
import com.example.domain.usecases.IGetPatientAppointmentsUseCase
import com.example.domain.usecases.LoginPatientUseCase
import com.example.domain.usecases.RegisterPatientUseCase
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, UseCaseModule::class])
interface AppComponent {
    fun userRepository(): IUserRepository
    fun patientRepository(): IPatientRepository
    fun appointmentRepository(): IAppointmentRepository

    // Use Cases
    fun registerPatientUseCase(): RegisterPatientUseCase
    fun loginPatientUseCase(): LoginPatientUseCase
    fun getPatientAppointmentsUseCase(): IGetPatientAppointmentsUseCase
}
package com.example.di

import com.example.domain.repositories.*
import com.example.domain.usecases.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, UseCaseModule::class])
interface AppComponent {
    // Repositories
    fun userRepository(): IUserRepository
    fun patientRepository(): IPatientRepository
    fun appointmentRepository(): IAppointmentRepository
    fun medicalServiceRepository(): IMedicalServiceRepository
    fun medicalProcedureRepository(): IMedicalProcedureRepository
    fun doctorRepository(): IDoctorRepository

    // Use Cases - Patient Management
    fun registerPatientUseCase(): RegisterPatientUseCase
    fun loginPatientUseCase(): LoginPatientUseCase
    fun getPatientAppointmentsUseCase(): IGetPatientAppointmentsUseCase
    fun bookAppointmentUseCase(): IBookAppointmentUseCase

    // Use Cases - Selection
    fun getMedicalServicesUseCase(): IGetMedicalServicesUseCase
    fun getMedicalProceduresUseCase(): IGetMedicalProceduresUseCase
    fun getProceduresByServiceUseCase(): IGetProceduresByServiceUseCase
    fun getDoctorsByServiceUseCase(): IGetDoctorsByServiceUseCase
    fun getAvailableDoctorsUseCase(): IGetAvailableDoctorsUseCase
}
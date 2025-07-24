package com.example.di

import com.example.domain.repositories.*
import com.example.domain.usecases.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideRegisterPatientUseCase(
        userRepository: IUserRepository,
        patientRepository: IPatientRepository
    ): RegisterPatientUseCase = RegisterPatientUseCase(
        userRepository = userRepository,
        patientRepository = patientRepository
    )

    @Provides
    @Singleton
    fun provideLoginPatientUseCase(
        userRepository: IUserRepository
    ): LoginPatientUseCase = LoginPatientUseCase(
        userRepository = userRepository
    )

    @Provides
    @Singleton
    fun provideGetPatientAppointmentsUseCase(
        userRepository: IUserRepository,
        patientRepository: IPatientRepository,
        appointmentRepository: IAppointmentRepository
    ): IGetPatientAppointmentsUseCase = GetPatientAppointmentsUseCase(
        userRepository = userRepository,
        patientRepository = patientRepository,
        appointmentRepository = appointmentRepository
    )

    @Provides
    @Singleton
    fun provideBookAppointmentUseCase(
        userRepository: IUserRepository,
        patientRepository: IPatientRepository,
        appointmentRepository: IAppointmentRepository
    ): IBookAppointmentUseCase = BookAppointmentUseCase(
        userRepository = userRepository,
        patientRepository = patientRepository,
        appointmentRepository = appointmentRepository
    )

    // Casos de uso de selección
    @Provides
    @Singleton
    fun provideGetMedicalServicesUseCase(
        medicalServiceRepository: IMedicalServiceRepository
    ): IGetMedicalServicesUseCase = GetMedicalServicesUseCase(
        medicalServiceRepository = medicalServiceRepository
    )

    @Provides
    @Singleton
    fun provideGetMedicalProceduresUseCase(
        medicalProcedureRepository: IMedicalProcedureRepository
    ): IGetMedicalProceduresUseCase = GetMedicalProceduresUseCase(
        medicalProcedureRepository = medicalProcedureRepository
    )

    @Provides
    @Singleton
    fun provideGetProceduresByServiceUseCase(
        medicalProcedureRepository: IMedicalProcedureRepository
    ): IGetProceduresByServiceUseCase = GetProceduresByServiceUseCase(
        medicalProcedureRepository = medicalProcedureRepository
    )

    @Provides
    @Singleton
    fun provideGetDoctorsByServiceUseCase(
        doctorRepository: IDoctorRepository
    ): IGetDoctorsByServiceUseCase = GetDoctorsByServiceUseCase(
        doctorRepository = doctorRepository
    )

    @Provides
    @Singleton
    fun provideGetAvailableDoctorsUseCase(
        doctorRepository: IDoctorRepository
    ): IGetAvailableDoctorsUseCase = GetAvailableDoctorsUseCase(
        doctorRepository = doctorRepository
    )
}

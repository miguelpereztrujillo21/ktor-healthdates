package com.example.di

import com.example.domain.repositories.IAppointmentRepository
import com.example.domain.repositories.IPatientRepository
import com.example.domain.repositories.IUserRepository
import com.example.domain.usecases.BookAppointmentUseCase
import com.example.domain.usecases.GetPatientAppointmentsUseCase
import com.example.domain.usecases.IBookAppointmentUseCase
import com.example.domain.usecases.IGetPatientAppointmentsUseCase
import com.example.domain.usecases.LoginPatientUseCase
import com.example.domain.usecases.RegisterPatientUseCase
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
}

package com.example.di

import com.example.data.repositories.*
import com.example.domain.repositories.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(): IUserRepository = UserRepositoryImpl()

    @Provides
    @Singleton
    fun providePatientRepository(): IPatientRepository = PatientRepositoryImpl()

    @Provides
    @Singleton
    fun provideAppointmentRepository(): IAppointmentRepository = AppointmentRepositoryImpl()

    @Provides
    @Singleton
    fun provideMedicalServiceRepository(): IMedicalServiceRepository = MedicalServiceRepositoryImpl()

    @Provides
    @Singleton
    fun provideMedicalProcedureRepository(): IMedicalProcedureRepository = MedicalProcedureRepositoryImpl()

    @Provides
    @Singleton
    fun provideDoctorRepository(): IDoctorRepository = DoctorRepositoryImpl()
}
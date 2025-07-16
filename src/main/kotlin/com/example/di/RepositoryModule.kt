package com.example.di

import com.example.data.repositories.PatientRepositoryImpl
import com.example.data.repositories.UserRepositoryImpl
import com.example.domain.repositories.IPatientRepository
import com.example.domain.repositories.IUserRepository
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
}
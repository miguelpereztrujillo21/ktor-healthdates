package com.example.di

import com.example.domain.repositories.IUserRepository
import com.example.domain.repositories.IPatientRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface AppComponent {
    fun userRepository(): IUserRepository
    fun patientRepository(): IPatientRepository
}
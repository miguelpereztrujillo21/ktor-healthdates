package com.example.di

import com.example.data.repositories.UserRepositoryImpl
import com.example.domain.repositories.IUserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(): IUserRepository = UserRepositoryImpl()
}
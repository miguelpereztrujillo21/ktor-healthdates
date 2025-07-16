package com.example.domain.usecases

interface ILoginPatientUseCase {
   suspend fun login(email: String, password: String): String?
}
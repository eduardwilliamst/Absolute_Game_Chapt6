package com.example.presentation

import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(token: String) {
        authRepository.saveToken(token)
    }
}
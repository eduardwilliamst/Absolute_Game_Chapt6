package com.example.di.domain

import com.example.domain.repository.AuthRepository
import com.example.domain.repository.ProfileRepository
import com.example.presentation.AuthenticateUseCase
import com.example.presentation.CheckLoginUseCase
import com.example.presentation.SaveTokenUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideAuthenticateUseCase(
        authRepository: AuthRepository,
    ): AuthenticateUseCase {
        return AuthenticateUseCase(authRepository)
    }

    @Provides
    fun provideCheckLoginUseCase(
        authRepository: AuthRepository,
    ): CheckLoginUseCase {
        return CheckLoginUseCase(authRepository)
    }

    @Provides
    fun provideSaveTokenUseCase(
        authRepository: AuthRepository,
    ): SaveTokenUseCase {
        return SaveTokenUseCase(authRepository)
    }
}
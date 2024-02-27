package com.example.domain.repository

import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun loadUsername(): Flow<String?>
    suspend fun loadEmail(): Flow<String?>
    suspend fun logout()
    suspend fun loadProfilePhoto(): Flow<String?>
    suspend fun saveProfilePhoto(profilePhoto: String)
}
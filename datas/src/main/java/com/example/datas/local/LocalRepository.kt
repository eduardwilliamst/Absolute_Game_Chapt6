package com.example.datas.local

import com.example.datas.helper.isPasswordValid
import com.example.datas.helper.isEmailValid
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.LoginRepository
import com.example.domain.repository.ProfileRepository
import com.example.domain.repository.RegisterRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager,
) : ProfileRepository, AuthRepository, RegisterRepository {

    override suspend fun validateInput(username: String, password: String): Boolean {
        delay(1000)
        return username.isNotEmpty()
                && username.isNotBlank()
                && password.isNotEmpty()
                && password.isNotBlank()
    }

    override suspend fun authenticate(username: String, password: String): String {
        delay(1000)
        return if (username == "anangkur" && password == "123456") {
            "token"
        } else {
            throw UnsupportedOperationException("username dan password salah!")
        }
    }

    override suspend fun saveToken(token: String) {
        dataStoreManager.saveToken(token)
    }

    override suspend fun isLoggedIn(): Flow<Boolean?> {
        return combine(
            dataStoreManager.loadToken(),
            dataStoreManager.loadUsername(),
            dataStoreManager.loadEmail(),
        ) { token, username, email ->
            (!token.isNullOrEmpty() && token.isNotBlank()) || (!username.isNullOrEmpty() && username.isNotBlank() && !email.isNullOrEmpty() && email.isNotBlank())
        }
    }

    override suspend fun validateInput(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        return username.isNotEmpty()
                && username.isNotBlank()
                && email.isNotEmpty()
                && email.isNotBlank()
                && email.isEmailValid()
                && password.isNotEmpty()
                && password.isNotBlank()
                && password.isPasswordValid()
                && password == confirmPassword
    }

    override suspend fun register(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        delay(1000)
        dataStoreManager.saveUsername(username)
        dataStoreManager.saveEmail(email)
    }

    override suspend fun loadUsername(): Flow<String?> {
        return dataStoreManager.loadUsername()
    }

    override suspend fun loadEmail(): Flow<String?> {
        return dataStoreManager.loadEmail()
    }

    override suspend fun logout() {
        delay(1000)
        dataStoreManager.deleteEmail()
        dataStoreManager.deleteUsername()
        dataStoreManager.deleteToken()
    }

    override suspend fun loadProfilePhoto(): Flow<String?> {
        return dataStoreManager.loadProfilePhoto()
    }

    override suspend fun saveProfilePhoto(profilePhoto: String) {
        dataStoreManager.saveProfilePhoto(profilePhoto)
    }
}
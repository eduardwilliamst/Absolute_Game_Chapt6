package com.example.di.domain

import com.example.datas.local.LocalRepository
import com.example.datas.remote.RemoteRepository
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.GenreRepository
import com.example.domain.repository.HomeRepository
import com.example.domain.repository.ProfileRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideAccountRepository(
        localRepository: LocalRepository,
    ): ProfileRepository {
        return localRepository
    }

    @Provides
    fun provideAuthRepository(
        localRepository: LocalRepository,
    ): AuthRepository {
        return localRepository
    }

    @Provides
    fun provideMovieRepository(
        remoteRepository: RemoteRepository,
    ): HomeRepository {
        return remoteRepository
    }

    @Provides
    fun provideGenreRepository(
        remoteRepository: RemoteRepository,
    ): GenreRepository {
        return remoteRepository
    }
}
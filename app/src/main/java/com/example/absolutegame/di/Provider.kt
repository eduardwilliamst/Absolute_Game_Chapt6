package com.example.absolutegame.di

import android.content.Context
import androidx.work.WorkManager
import com.example.absolutegame.Application
import com.example.datas.local.DataStoreManager
import com.example.datas.local.LocalRepository
import com.example.datas.remote.RemoteRepository
import com.example.datas.remote.service.GameService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Provider(
    val context: Application,
) {
    val workManager = WorkManager.getInstance(context)

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.rawg.io/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val gameService: GameService = retrofit.create(GameService::class.java)

    private val dataStoreManager = DataStoreManager(context)

    val localRepository: LocalRepository =
        LocalRepository(
            dataStoreManager = dataStoreManager,
        )

    val remoteRepository: RemoteRepository =
        RemoteRepository(
            gamesService = gameService,
        )
}
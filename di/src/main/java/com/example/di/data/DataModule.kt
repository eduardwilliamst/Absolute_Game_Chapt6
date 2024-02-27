package com.example.di.data

import android.content.Context
import androidx.work.WorkManager
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.datas.local.DataStoreManager
import com.example.datas.local.LocalRepository
import com.example.datas.remote.RemoteRepository
import com.example.datas.remote.service.GameService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {
    @Provides
    fun provideWorkManager(context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Provides
    fun provideLocalRepository(
        dataStoreManager: com.example.datas.local.DataStoreManager,
    ): com.example.datas.local.LocalRepository {
        return com.example.datas.local.LocalRepository(dataStoreManager)
    }

    @Provides
    fun provideRemoteRepository(
        gameService: GameService
    ): RemoteRepository {
        return RemoteRepository(gameService)
    }

    @Provides
    fun provideDataStoreManager(
        context: Context,
    ): com.example.datas.local.DataStoreManager {
        return com.example.datas.local.DataStoreManager(context)
    }

    @Provides
    fun provideGameService(
        retrofit: Retrofit,
    ): GameService {
        return retrofit.create(GameService::class.java)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideOkhttpClient(
        chuckerInterceptor: ChuckerInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideBaseUrl(): String {
        return "https://api.rawg.io/api/"
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideChuckerInterceptor(context: Context): ChuckerInterceptor {
        return ChuckerInterceptor(context)
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }
}
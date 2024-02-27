package com.example.datas.remote.service

import com.example.datas.remote.response.GenreResponse
import com.example.datas.remote.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GameService {

    @GET("games")
    suspend fun fetchGames(
        @Query("key") apiKey: String = "9164f0263dc545f496c0bd00ba00b08c",
    ): Response

    @GET("genres")
    suspend fun fetchGenres(
        @Query("key") apiKey: String = "9164f0263dc545f496c0bd00ba00b08c",
    ): GenreResponse
}
package com.example.domain.repository

import com.example.domain.Game

interface HomeRepository {
    suspend fun fetchGames(): List<Game>

}
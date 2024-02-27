package com.example.domain.repository

import com.example.domain.Game

interface FavoriteRepository {
    suspend fun getFavorites(): List<Game>

}
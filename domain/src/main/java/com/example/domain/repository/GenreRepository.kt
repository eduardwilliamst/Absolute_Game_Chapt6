package com.example.domain.repository

import com.example.domain.Genre

interface GenreRepository {
    suspend fun fetchGenres(): List<Genre>
}
package com.example.datas.remote

import com.example.datas.remote.response.toGame
import com.example.datas.remote.response.toGenre
import com.example.datas.remote.service.GameService
import com.example.domain.Game
import com.example.domain.Genre
import com.example.domain.repository.GenreRepository
import com.example.domain.repository.HomeRepository

class RemoteRepository (
    private val gamesService: GameService,
) : com.example.domain.repository.HomeRepository, com.example.domain.repository.GenreRepository {
    override suspend fun fetchGames(): List<com.example.domain.Game> {
        return gamesService.fetchGames().results?.map { result -> result.toGame() }.orEmpty()
    }

    override suspend fun fetchGenres(): List<com.example.domain.Genre> {
        return gamesService.fetchGenres().results?.map { result -> result.toGenre() }.orEmpty()
    }
}
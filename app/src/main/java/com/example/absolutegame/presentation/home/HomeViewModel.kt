package com.example.absolutegame.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datas.local.LocalRepository
import com.example.domain.Game
import com.example.domain.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val homeRepository: HomeRepository,
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _games = MutableLiveData<List<com.example.domain.Game>>()
    val games: LiveData<List<com.example.domain.Game>> = _games

    fun fetchGames() {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                homeRepository.fetchGames()
            }.onFailure { exception ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _error.value = exception.message
                }
            }.onSuccess { games ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _games.value = games
                }
            }
        }
    }

    fun saveFavorite(game: com.example.domain.Game) {
        viewModelScope.launch(Dispatchers.IO) {
//            localRepository.saveFavorite(game)
        }
    }
}
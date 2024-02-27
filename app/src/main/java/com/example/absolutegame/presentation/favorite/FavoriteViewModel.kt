package com.example.absolutegame.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Game
import com.example.domain.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteViewModel(
    private val favoriteRepository: com.example.domain.repository.FavoriteRepository
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _favorite = MutableLiveData<List<com.example.domain.Game>>()
    val games: LiveData<List<com.example.domain.Game>> = _favorite

    fun loadFavoriteGames() {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                favoriteRepository.getFavorites()
            }.onFailure { exception ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _error.value = exception.message
                }
            }.onSuccess { favorites ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _favorite.value = favorites
                }
            }
        }
    }
}

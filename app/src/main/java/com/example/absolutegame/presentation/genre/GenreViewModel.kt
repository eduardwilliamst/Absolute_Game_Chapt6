package com.example.absolutegame.presentation.genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Genre
import com.example.domain.repository.GenreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenreViewModel(
    private val genreRepository: com.example.domain.repository.GenreRepository,
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _genres = MutableLiveData<List<com.example.domain.Genre>>()
    val genres: LiveData<List<com.example.domain.Genre>> = _genres

    fun fetchGenres() {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                genreRepository.fetchGenres()
            }.onFailure { exception ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _error.value = exception.message
                }
            }.onSuccess { genres ->
                withContext(Dispatchers.Main) {
                    _loading.value = false
                    _genres.value = genres
                }
            }
        }
    }
}
package com.capgemini.architectcoders.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.architectcoders.data.Movie
import com.capgemini.architectcoders.data.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// MVI is a list of actions**
sealed interface DetailAction {
    data object FavoriteClick : DetailAction
    data object MessageShown : DetailAction
}

class DetailViewModel(
    private val id: Int,
    private val repository: MoviesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? = null,
        val message: String? = null
    )

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            repository.findMovieById(id).collect { movie ->
                _state.value = UiState(loading = false, movie = movie)
            }
        }
    }

    fun onAction(action : DetailAction) {
        // Juntando la tupla del estado antiguo y la accion se genera un estado nuevo
        // Se conoce con el nombre reducer
        when(action) {
            is DetailAction.FavoriteClick -> _state.update { it.copy(message = "Favorite clicked") }
            is DetailAction.MessageShown -> _state.update { it.copy(message = null) }
        }
    }

    fun onFavoriteClick() {
        _state.update { it.copy(message = "Favorite clicked") }
    }

    fun onMessageShown() {
        _state.update { it.copy(message = null) }
    }
}
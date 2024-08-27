package com.capgemini.architectcoders.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.architectcoders.data.Movie
import com.capgemini.architectcoders.data.MoviesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailViewModel(
    id: Int,
    private val repository: MoviesRepository
) : ViewModel() {

    val state: StateFlow<UiSate> = repository.findMovieById(id)
        .map { movie ->
            UiSate(movie = movie)
        }.stateIn(
            scope = viewModelScope, // once we change flow to stateflow we need always a default value
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiSate(loading = true)
        )

    data class UiSate(
        val loading: Boolean = false,
        val movie: Movie? = null
    )

    fun onFavoriteClicked() {
        state.value.movie?.let {
            viewModelScope.launch {
                repository.toggleFavoriteMovie(it)
            }
        }
    }
}
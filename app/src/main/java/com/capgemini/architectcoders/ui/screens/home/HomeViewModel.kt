package com.capgemini.architectcoders.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.architectcoders.data.Movie
import com.capgemini.architectcoders.data.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MoviesRepository): ViewModel() {


    private val _state = MutableStateFlow(UiSate())
    val state: StateFlow<UiSate> = _state.asStateFlow()

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiSate(loading = true)
            repository.movies.collect { movies ->
                _state.value = UiSate(loading= false, movies = movies)
            }
        }
    }

    data class UiSate(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )
}
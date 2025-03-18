package com.capgemini.architectcoders.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.architectcoders.domain.movie.data.MoviesRepository
import com.capgemini.architectcoders.domain.movie.entities.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(repository: MoviesRepository): ViewModel() {
    private val uiReady = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<UiSate> = uiReady
        .filter { it }
        .flatMapLatest { repository.movies }
        .map { UiSate(movies = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiSate(true)
        )

    fun onUiReady() {
        uiReady.value = true
    }

    data class UiSate(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )
}
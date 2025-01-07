package com.capgemini.architectcoders.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.architectcoders.domain.MovieModel
import com.capgemini.architectcoders.usecases.FetchMoviesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val fetchMoviesUseCase: FetchMoviesUseCase
): ViewModel() {

    private val uiReady = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<UiSate> = uiReady
        .filter { it }
        .flatMapLatest { fetchMoviesUseCase() }
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
        val movies: List<MovieModel> = emptyList()
    )
}
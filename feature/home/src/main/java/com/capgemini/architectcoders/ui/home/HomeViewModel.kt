package com.capgemini.architectcoders.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.architectcoders.domain.movie.entities.Movie
import com.capgemini.architectcoders.domain.movie.usecases.FetchMoviesUseCase
import com.capgemini.architectcoders.ui.common.Result
import com.capgemini.architectcoders.ui.common.stateAsResultIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest

import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(private val fetchMoviesUseCase: FetchMoviesUseCase): ViewModel() {
    private val uiReady = MutableStateFlow(false)

    val state: StateFlow<Result<List<Movie>>> = uiReady
        .filter { it }
        .flatMapLatest { fetchMoviesUseCase() }
        .stateAsResultIn(viewModelScope)

    fun onUiReady() {
        uiReady.value = true
    }
}
package com.capgemini.architectcoders.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.architectcoders.domain.movie.entities.Movie

import com.capgemini.architectcoders.domain.movie.usecases.FindMovieByIdUseCase
import com.capgemini.architectcoders.domain.movie.usecases.ToggleFavoriteUseCase
import com.capgemini.architectcoders.ui.common.stateAsResultIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.flow.StateFlow
import com.capgemini.architectcoders.ui.common.Result
import com.capgemini.architectcoders.ui.common.ifSuccess

@HiltViewModel
class DetailViewModel @Inject constructor(
    @Named("movieId") id: Int,
    findMovieByIdUseCase: FindMovieByIdUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

   val state: StateFlow<Result<Movie>> = findMovieByIdUseCase(id)
           .stateAsResultIn(scope = viewModelScope)

    fun onFavoriteClicked() {
        state.value.ifSuccess{
            viewModelScope.launch {
                toggleFavoriteUseCase(it)
            }
        }
    }
}
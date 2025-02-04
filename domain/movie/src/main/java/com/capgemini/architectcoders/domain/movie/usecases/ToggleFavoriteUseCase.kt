package com.capgemini.architectcoders.domain.movie.usecases

import com.capgemini.architectcoders.domain.movie.data.MoviesRepository
import com.capgemini.architectcoders.domain.movie.entities.Movie

class ToggleFavoriteUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(movie: Movie) {
        repository.toggleFavoriteMovie(movie)
    }
}
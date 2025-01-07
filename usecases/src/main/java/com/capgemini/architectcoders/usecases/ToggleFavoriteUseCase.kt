package com.capgemini.architectcoders.usecases

import com.capgemini.architectcoders.domain.MovieModel
import com.capgemini.architectcoders.data.MoviesRepository

class ToggleFavoriteUseCase(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(movie: MovieModel) {
        moviesRepository.toggleFavoriteMovie(movie)
    }
}
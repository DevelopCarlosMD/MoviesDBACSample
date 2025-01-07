package com.capgemini.architectcoders.usecases

import com.capgemini.architectcoders.data.MoviesRepository

class FetchMoviesUseCase(private val moviesRepository: MoviesRepository) {
    operator fun invoke() = moviesRepository.movies
}
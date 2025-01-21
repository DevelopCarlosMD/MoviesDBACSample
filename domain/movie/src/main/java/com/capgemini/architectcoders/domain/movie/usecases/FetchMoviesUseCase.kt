package com.capgemini.architectcoders.domain.movie.usecases


import com.capgemini.architectcoders.domain.movie.data.MoviesRepository
import com.capgemini.architectcoders.domain.movie.entities.Movie
import kotlinx.coroutines.flow.Flow

class FetchMoviesUseCase(private val moviesRepository: MoviesRepository) {
    operator fun invoke(): Flow<List<Movie>> = moviesRepository.movies
}
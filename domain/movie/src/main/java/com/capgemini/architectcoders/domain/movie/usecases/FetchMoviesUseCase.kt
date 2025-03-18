package com.capgemini.architectcoders.domain.movie.usecases


import com.capgemini.architectcoders.domain.movie.data.MoviesRepository
import com.capgemini.architectcoders.domain.movie.entities.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    operator fun invoke(): Flow<List<Movie>> = moviesRepository.movies
}
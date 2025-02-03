package com.capgemini.architectcoders.domain.movie.usecases


import com.capgemini.architectcoders.domain.movie.data.MoviesRepository
import com.capgemini.architectcoders.domain.movie.entities.Movie
import kotlinx.coroutines.flow.Flow

class FindMovieByIdUseCase(private val repository: MoviesRepository) {
    operator fun invoke(id: Int): Flow<Movie> = repository.findMovieById(id)
}
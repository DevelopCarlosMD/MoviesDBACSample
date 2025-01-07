package com.capgemini.architectcoders.usecases

import com.capgemini.architectcoders.domain.MovieModel
import com.capgemini.architectcoders.data.MoviesRepository
import kotlinx.coroutines.flow.Flow

class FindMovieByIdUseCase(
    private val repository: MoviesRepository
) {
    operator fun invoke(id: Int) : Flow<MovieModel?> = repository.findMovieById(id)
}
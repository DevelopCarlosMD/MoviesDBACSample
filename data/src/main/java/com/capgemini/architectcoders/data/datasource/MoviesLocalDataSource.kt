package com.capgemini.architectcoders.data.datasource

import com.capgemini.architectcoders.domain.MovieModel
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    val movies: Flow<List<MovieModel>>
    fun findMovieById(id: Int): Flow<MovieModel?>

    suspend fun isEmpty(): Boolean

    suspend fun saveMovies(movies: List<MovieModel>)
}
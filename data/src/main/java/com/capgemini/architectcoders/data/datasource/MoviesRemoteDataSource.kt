package com.capgemini.architectcoders.data.datasource

import com.capgemini.architectcoders.domain.MovieModel

interface MoviesRemoteDataSource {
    suspend fun fetchPopularMovies(region: String): List<MovieModel>
    suspend fun findMovieById(id: Int): MovieModel
}
package com.capgemini.architectcoders.domain.movie.data

import com.capgemini.architectcoders.domain.movie.entities.Movie

interface MoviesRemoteDataSource {
    suspend fun fetchPopularMovies(region: String): List<Movie>

    suspend fun findMovieById(id: Int): Movie
}
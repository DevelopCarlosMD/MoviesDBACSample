package com.capgemini.architectcoders.data.datasource

import com.capgemini.architectcoders.data.Movie
import com.capgemini.architectcoders.data.datasource.database.MoviesDao

class MoviesLocalDataSource(private val moviesDao: MoviesDao) {

    val movies = moviesDao.fetchPopularMovies()

    fun findMovieById(id: Int) = moviesDao.findMovieById(id)

    suspend fun isEmpty() = moviesDao.countMovies() == 0

    suspend fun saveMovies(movies: List<Movie>) = moviesDao.saveMovies(movies)
}
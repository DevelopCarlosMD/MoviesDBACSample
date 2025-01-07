package com.capgemini.architectcoders.data

import com.capgemini.architectcoders.data.datasource.MoviesLocalDataSource
import com.capgemini.architectcoders.data.datasource.MoviesRemoteDataSource
import com.capgemini.architectcoders.domain.MovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach

class MoviesRepository(
    private val regionRepository: RegionRepository,
    private val localDataSource: MoviesLocalDataSource,
    private val remoteDataSource: MoviesRemoteDataSource
) {

    val movies: Flow<List<MovieModel>> = localDataSource.movies.onEach { localMovies ->
        if (localMovies.isEmpty()) {
            val region = regionRepository.findLastRegion()
            val remoteMovies = remoteDataSource.fetchPopularMovies(region)
            localDataSource.saveMovies(remoteMovies)
        }
    }

    fun findMovieById(id: Int): Flow<MovieModel?> =
        localDataSource.findMovieById(id).onEach {
            if (it == null) {
                val remoteMovie = remoteDataSource.findMovieById(id)
                localDataSource.saveMovies(listOf(remoteMovie))
            }
        }.filterNotNull()

    suspend fun toggleFavoriteMovie(movie: MovieModel) {
        localDataSource.saveMovies(listOf(movie.copy(favorite = !movie.favorite)))
    }
}
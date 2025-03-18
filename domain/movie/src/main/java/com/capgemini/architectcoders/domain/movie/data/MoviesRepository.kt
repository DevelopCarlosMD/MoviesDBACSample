package com.capgemini.architectcoders.domain.movie.data

import com.capgemini.architectcoders.domain.movie.entities.Movie
import com.capgemini.architectcoders.domain.region.data.RegionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val regionRepository: RegionRepository,
    private val localDataSource: MoviesLocalDataSource,
    private val remoteDataSource: MoviesRemoteDataSource
) {
    val movies: Flow<List<Movie>>
        get() = localDataSource.movies.onEach { localMovies ->
        if (localMovies.isEmpty()) {
            val remoteMoves = remoteDataSource.fetchPopularMovies(regionRepository.findLastRegion())
            localDataSource.saveMovies(remoteMoves)
        }
    }

    fun findMovieById(id: Int): Flow<Movie> = localDataSource.findMovieById(id)
        .onEach { movie ->
            if (movie == null) {
                val remoteMovie = remoteDataSource.findMovieById(id)
                localDataSource.saveMovies(listOf(remoteMovie))
            }
        }
        .filterNotNull()

    suspend fun toggleFavoriteMovie(movie: Movie) {
        localDataSource.saveMovies(listOf(movie.copy(isFavorite = !movie.isFavorite)))
    }
}


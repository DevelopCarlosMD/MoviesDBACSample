package com.capgemini.architectcoders.framework.movie.database

import com.capgemini.architectcoders.domain.movie.data.MoviesLocalDataSource
import com.capgemini.architectcoders.domain.movie.entities.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class MoviesRoomDataSource(private val moviesDao: MoviesDao):
    MoviesLocalDataSource {

    override val movies: Flow<List<Movie>> =
        moviesDao.fetchPopularMovies().map { it.toDomainMovies() }

    override fun findMovieById(id: Int): Flow<Movie?> =
        moviesDao.findMovieById(id).map { it?.toDomainMovie() }

    override suspend fun isEmpty()= moviesDao.countMovies() == 0

    override suspend fun saveMovies(movies: List<Movie>) = moviesDao.saveMovies(movies.toDbMovies())
}

private fun MovieEntity.toDomainMovie() = Movie(
    id = id,
    title = title,
    overview = overview,
    poster = poster,
    backdrop = backdrop,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    isFavorite = isFavorite,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    popularity = popularity
)

private fun List<MovieEntity>.toDomainMovies() = map { it.toDomainMovie() }

private fun Movie.toDbMovie() = MovieEntity(
    id = id,
    title = title,
    overview = overview,
    poster = poster,
    backdrop = backdrop,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    isFavorite = isFavorite,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    popularity = popularity
)

private fun List<Movie>.toDbMovies() = map { it.toDbMovie() }
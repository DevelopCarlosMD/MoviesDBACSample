package com.capgemini.architectcoders.framework

import com.capgemini.architectcoders.data.datasource.MoviesLocalDataSource
import com.capgemini.architectcoders.domain.MovieModel
import com.capgemini.architectcoders.framework.database.MovieEntity
import com.capgemini.architectcoders.framework.database.MoviesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRoomDataSource(private val moviesDao: MoviesDao) : MoviesLocalDataSource {

    override val movies: Flow<List<MovieModel>> = moviesDao.fetchPopularMovies().map { movies -> movies.map { it.toDomainMovie() } }

    override fun findMovieById(id: Int) = moviesDao.findMovieById(id).map { it?.toDomainMovie() }

    override suspend fun isEmpty() = moviesDao.countMovies() == 0

    override suspend fun saveMovies(movies: List<MovieModel>) = moviesDao.saveMovies(movies.map { it.toDbMovie() })
}

private fun MovieModel.toDbMovie() = MovieEntity(
    id = id,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    poster = poster,
    backdrop = backdrop,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    popularity = popularity,
    voteAverage = voteAverage,
    favorite = favorite
)

private fun MovieEntity.toDomainMovie(): MovieModel = MovieModel(
    id = id,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    poster = poster,
    backdrop = backdrop,
    originalTitle = originalTitle,
    originalLanguage = originalLanguage,
    popularity = popularity,
    voteAverage = voteAverage,
    favorite = favorite
)
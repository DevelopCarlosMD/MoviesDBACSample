package com.capgemini.architectcoders.framework

import com.capgemini.architectcoders.data.datasource.MoviesRemoteDataSource
import com.capgemini.architectcoders.domain.MovieModel
import com.capgemini.architectcoders.framework.remote.MoviesService
import com.capgemini.architectcoders.framework.remote.RemoteMovie

class MoviesServerDataSource(private val moviesService: MoviesService) : MoviesRemoteDataSource {

    override suspend fun fetchPopularMovies(region: String): List<MovieModel> {
        return moviesService.fetchPopularMovies(region)
            .results
            .map { it.toDomainModel() }
    }

    override suspend fun findMovieById(id: Int): MovieModel {
        return moviesService.fetchMovieById(id).toDomainModel()
    }
}

private fun RemoteMovie.toDomainModel() = MovieModel(
    id,
    title,
    overview,
    releaseDate,
    "https://image.tmdb.org/t/p/w185/$posterPath",
    backdropPath.let { "https://image.tmdb.org/t/p/w780/$it" },
    originalLanguage,
    originalTitle,
    popularity,
    voteAverage,
    false
)

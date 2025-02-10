package com.capgemini.architectcoders.framework.movie.network

import com.capgemini.architectcoders.domain.movie.data.MoviesRemoteDataSource
import com.capgemini.architectcoders.domain.movie.entities.Movie
import javax.inject.Inject

internal class MoviesServerDataSource @Inject constructor(private val moviesService: MoviesService) : MoviesRemoteDataSource {

    override suspend fun fetchPopularMovies(region: String): List<Movie> =
        moviesService.fetchPopularMovies(region)
            .results
            .map { it.toDomainModel() }

    override suspend fun findMovieById(id: Int): Movie =
        moviesService.fetchMovieById(id).toDomainModel()
}

private fun RemoteMovie.toDomainModel() = Movie(
    id,
    title,
    overview,
    releaseDate,
    "https://image.tmdb.org/t/p/w185/$posterPath",
    backdropPath?.let { "https://image.tmdb.org/t/p/w780/$it" },
    originalLanguage,
    originalTitle,
    popularity,
    voteAverage,
    false
)
package com.capgemini.architectcoders.data.datasource

import com.capgemini.architectcoders.data.Movie
import com.capgemini.architectcoders.data.datasource.remote.MoviesClient
import com.capgemini.architectcoders.data.datasource.remote.RemoteMovie

class MoviesRemoteDataSource {

    suspend fun fetchPopularMovies(region: String): List<Movie> {
        return MoviesClient.instance.fetchPopularMovies(region)
            .results
            .map { it.toDomainModel() }
    }

    suspend fun findMovieById(id: Int): Movie {
        return MoviesClient.instance.fetchMovieById(id).toDomainModel()
    }
}

private fun RemoteMovie.toDomainModel() = Movie(
    id,
    title,
    overview,
    releaseDate,
    "https://image.tmdb.org/t/p/w185/$posterPath",
    backdropPath.let { "https://image.tmdb.org/t/p/w780/$it" },
    originalLanguage,
    originalTitle,
    popularity,
    voteAverage
)
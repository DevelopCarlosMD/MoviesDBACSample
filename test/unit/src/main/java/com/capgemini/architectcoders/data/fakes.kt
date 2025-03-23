package com.capgemini.architectcoders.data


import com.capgemini.architectcoders.domain.movie.data.MoviesLocalDataSource
import com.capgemini.architectcoders.domain.movie.data.MoviesRemoteDataSource
import com.capgemini.architectcoders.domain.movie.data.MoviesRepository
import com.capgemini.architectcoders.domain.movie.entities.Movie
import com.capgemini.architectcoders.domain.region.data.DEFAULT_REGION
import com.capgemini.architectcoders.domain.region.data.LocationDataSource
import com.capgemini.architectcoders.domain.region.data.RegionDataSource
import com.capgemini.architectcoders.domain.region.data.RegionRepository
import com.capgemini.architectcoders.sampleMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

fun buildMoviesRepositoryWith(
    localData: List<Movie> = emptyList(),
    remoteData: List<Movie> = emptyList()
): MoviesRepository {
    val regionRepository = RegionRepository(FakeRegionDataSource())
    val localDataSource = FakeLocalDataSource().apply { inMemoryMovies.value = localData }
    val remoteDataSource = FakeRemoteDataSource().apply { movies = remoteData }
    return MoviesRepository(regionRepository, localDataSource, remoteDataSource)
}

class FakeRemoteDataSource : MoviesRemoteDataSource {
    var movies = sampleMovies(1,2,3,4)

    override suspend fun fetchPopularMovies(region: String) = movies

    override suspend fun findMovieById(id: Int) = movies.first() { it.id == id }
}

class FakeRegionDataSource : RegionDataSource {
    var region: String = DEFAULT_REGION

    override suspend fun findLastRegion(): String = region
}

class FakeLocalDataSource : MoviesLocalDataSource {

    val inMemoryMovies = MutableStateFlow<List<Movie>>(emptyList())

    override val movies: Flow<List<Movie>>
        get() = inMemoryMovies

    override fun findMovieById(id: Int) = inMemoryMovies.map {
        it.firstOrNull { movie -> movie.id == id }
    }

    override suspend fun saveMovies(movies: List<Movie>) {
        inMemoryMovies.value = movies
    }
}
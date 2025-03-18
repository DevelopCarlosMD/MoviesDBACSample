package com.capgemini.architectcoders.domain.movie.data


import com.capgemini.architectcoders.domain.region.data.DEFAULT_REGION
import com.capgemini.architectcoders.domain.region.data.RegionRepository
import com.capgemini.architectcoders.sampleMovie
import com.capgemini.architectcoders.sampleMovies
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argThat
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class MoviesRepositoryTest {

    @Mock
    lateinit var regionRepository: RegionRepository

    @Mock
    lateinit var localDataSource: MoviesLocalDataSource

    @Mock
    lateinit var remoteDataSource: MoviesRemoteDataSource

    private lateinit var repository: MoviesRepository
    private val localMovies = sampleMovies(1, 2)
    private val remoteMovies = sampleMovies(3, 4)

    @Before
    fun setup() {
        repository = MoviesRepository(regionRepository, localDataSource, remoteDataSource)
    }

    @Test
    fun `Popular movies are taken from local data source if available`() : Unit = runBlocking {
        whenever(localDataSource.movies).thenReturn(flowOf(localMovies))

        val result = repository.movies

        assertEquals(localMovies, result.first())
    }

    @Test
    fun `Popular movies are saved to local data source when it's empty`(): Unit = runBlocking {
        whenever(localDataSource.movies).thenReturn(flowOf(emptyList()))
        whenever(regionRepository.findLastRegion()).thenReturn(DEFAULT_REGION)
        whenever(remoteDataSource.fetchPopularMovies(DEFAULT_REGION)).thenReturn(remoteMovies)

        repository.movies.first()

        verify(localDataSource).saveMovies(remoteMovies)
    }

    @Test
    fun `Toggling favorite updates local data source`(): Unit = runBlocking {
        val movie = sampleMovie(3)
        repository.toggleFavoriteMovie(movie)

        verify(localDataSource).saveMovies(argThat { get(0).id == 3 })
    }

    @Test
    fun `Switching favorite marks as favorite an unfavorite movie`(): Unit = runBlocking {
        val movie = sampleMovie(1).copy(isFavorite = false)
        repository.toggleFavoriteMovie(movie)

        verify(localDataSource).saveMovies(argThat { get(0).isFavorite })
    }

    @Test
    fun `Switching favorite marks as unfavorite a favorite movie`(): Unit = runBlocking {
        val movie = sampleMovie(1).copy(isFavorite = true)
        repository.toggleFavoriteMovie(movie)

        verify(localDataSource).saveMovies(argThat { !get(0).isFavorite })
    }
}
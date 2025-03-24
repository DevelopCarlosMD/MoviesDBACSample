package com.capgemini.architectcoders

import androidx.test.rule.GrantPermissionRule
import com.capgemini.architectcoders.data.server.MockWebServerRule
import com.capgemini.architectcoders.data.server.fromJson
import com.capgemini.architectcoders.domain.movie.data.MoviesRemoteDataSource
import com.capgemini.architectcoders.domain.movie.data.MoviesRepository
import com.capgemini.architectcoders.framework.movie.database.MovieEntity
import com.capgemini.architectcoders.framework.movie.database.MoviesDao
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

@HiltAndroidTest
class ExampleInstrumentedTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val mockWebServerRule = MockWebServerRule()

    @get:Rule(order = 2)
    val locationPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        "android.permission.ACCESS_COARSE_LOCATION"
    )

    @Inject
    lateinit var moviesRepository: MoviesRepository

    @Inject
    lateinit var moviesDao: MoviesDao

    @Inject
    lateinit var remoteDataSource: MoviesRemoteDataSource

    @Before
    fun setUp() {
        mockWebServerRule.server.enqueue(
            MockResponse().fromJson("popular_movies.json")
        )

        hiltRule.inject()
    }

    @Test
    fun test_repository_initialized() {
        runBlocking {
            val movies = moviesRepository.movies.first()
            assertTrue(movies.isEmpty())
        }
    }

    @Test
    fun check_4_items_in_db() = runTest {
        moviesDao.saveMovies(buildDatabaseMovies(1, 2, 3, 4))
        assertEquals(4,  moviesDao.fetchPopularMovies().first().size)
    }

    @Test
    fun check_6_items_in_db() = runTest {
        moviesDao.saveMovies(buildDatabaseMovies(1, 2, 3, 4, 5, 6))
        assertEquals(6, moviesDao.fetchPopularMovies().first().size)
    }

    @Test
    fun check_mock_server_is_working() = runTest {
        val movies = remoteDataSource.fetchPopularMovies("EN")

        assertEquals("The Batman", movies[0].title)
    }
}

fun buildDatabaseMovies(vararg id: Int) = id.map {
    MovieEntity(
        id = it,
        title = "Title $it",
        overview = "Overview $it",
        releaseDate = "01/01/2025",
        poster = "",
        backdrop = "",
        originalLanguage = "EN",
        originalTitle = "Original Title $it",
        popularity = 5.0,
        voteAverage = 5.1,
        isFavorite = false
    )
}
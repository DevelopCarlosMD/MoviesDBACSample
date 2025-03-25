package com.capgemini.architectcoders.ui.detail

import app.cash.turbine.test
import com.capgemini.architectcoders.data.buildMoviesRepositoryWith
import com.capgemini.architectcoders.domain.movie.usecases.FindMovieByIdUseCase
import com.capgemini.architectcoders.domain.movie.usecases.ToggleFavoriteUseCase
import com.capgemini.architectcoders.sampleMovie
import com.capgemini.architectcoders.sampleMovies
import com.capgemini.architectcoders.testrules.CoroutinesTestRule
import com.capgemini.architectcoders.ui.common.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var vm: DetailViewModel

    @Before
    fun setup() {
        val moviesRepository = buildMoviesRepositoryWith(localData = sampleMovies(1, 2))

        vm = DetailViewModel(
            2,
            FindMovieByIdUseCase(moviesRepository),
            ToggleFavoriteUseCase(moviesRepository)
        )
    }

    @Test
    fun `UI is updated with the movie on start`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(sampleMovie(2)), awaitItem())
        }
    }

    @Test
    fun `Favorite is updated in local data source`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(sampleMovie(2)), awaitItem())

            vm.onFavoriteClicked()
            runCurrent()

            assertEquals(Result.Success(sampleMovie(2).copy(isFavorite = true)), awaitItem())
        }
    }
}
package com.capgemini.architectcoders.ui.detail

import app.cash.turbine.test
import com.capgemini.architectcoders.domain.movie.usecases.FindMovieByIdUseCase
import com.capgemini.architectcoders.domain.movie.usecases.ToggleFavoriteUseCase
import com.capgemini.architectcoders.sampleMovie
import com.capgemini.architectcoders.testrules.CoroutinesTestRule
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import com.capgemini.architectcoders.ui.common.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import org.mockito.kotlin.any
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var findMovieByIdUseCase: FindMovieByIdUseCase

    @Mock
    lateinit var toggleFavoriteUseCase: ToggleFavoriteUseCase

    private lateinit var vm: DetailViewModel

    private val movie = sampleMovie(3)

    @Before
    fun setUp() {
        whenever(findMovieByIdUseCase(3)).thenReturn(flowOf(movie))
        vm = DetailViewModel(3, findMovieByIdUseCase, toggleFavoriteUseCase)
    }

    @Test
    fun `UI is updated with the movie on start`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(movie), awaitItem())
        }
    }

    @Test
    fun `Favorite action calls the corresponding use case`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(movie), awaitItem())

            vm.onFavoriteClicked()
            runCurrent()

            verify(toggleFavoriteUseCase).invoke(any())
        }
    }
}
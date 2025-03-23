package com.capgemini.architectcoders.ui.home

import app.cash.turbine.test
import com.capgemini.architectcoders.data.buildMoviesRepositoryWith
import com.capgemini.architectcoders.domain.movie.entities.Movie
import com.capgemini.architectcoders.domain.movie.usecases.FetchMoviesUseCase
import com.capgemini.architectcoders.sampleMovies
import com.capgemini.architectcoders.testrules.CoroutinesTestRule
import com.capgemini.architectcoders.ui.common.Result
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeIntegrationTests {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `Data is loaded from server when local data source is empty`() = runTest {
        val remoteData = sampleMovies(1, 2)
        val vm = buildViewModelWith(remoteData = remoteData)
        vm.onUiReady()

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(emptyList<Movie>()), awaitItem())
            assertEquals(Result.Success(remoteData), awaitItem())
        }
    }

    @Test
    fun `Data is loaded from local source when available`() = runTest {
        val localData = sampleMovies(1, 2)
        val vm = buildViewModelWith(localData = localData)

        vm.onUiReady()
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(localData), awaitItem())
        }
    }

    private fun buildViewModelWith(
        localData: List<Movie> = emptyList(),
        remoteData: List<Movie> = emptyList()
    ) =
        HomeViewModel(
            FetchMoviesUseCase(
                buildMoviesRepositoryWith(
                    localData = localData,
                    remoteData = remoteData
                )
            )
        )
}
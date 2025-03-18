package com.capgemini.architectcoders.domain.movie.usecases

import com.capgemini.architectcoders.sampleMovies
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


class FetchMoviesUseCaseTest {

    // Test aaa (arrange add assert)
    @Test
    fun `Invoke calls repository`() {
        val movieFlow = flowOf(sampleMovies(1,2,3))

        val useCase = FetchMoviesUseCase(mock {
            on { movies } doReturn  movieFlow
        })

        val result = useCase()

        assertEquals(movieFlow, result)
    }
}
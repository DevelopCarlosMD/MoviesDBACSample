package com.capgemini.architectcoders.domain.movie.usecases


import com.capgemini.architectcoders.sampleMovie
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


class FindMovieByIdUseCaseTest {
    // Test aaa (arrange add assert)
    @Test
    fun `Invoke calls repository`() {
        val movieFlow = flowOf(sampleMovie(1))

        val useCase = FindMovieByIdUseCase(mock {
            on { findMovieById(1) } doReturn  movieFlow
        })

        val result = useCase(1)

        assertEquals(movieFlow, result)
    }
}
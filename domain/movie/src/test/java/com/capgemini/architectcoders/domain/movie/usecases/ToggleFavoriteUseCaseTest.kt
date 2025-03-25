package com.capgemini.architectcoders.domain.movie.usecases

import com.capgemini.architectcoders.domain.movie.data.MoviesRepository
import com.capgemini.architectcoders.sampleMovie
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify


class ToggleFavoriteUseCaseTest {

    @Test
    fun `Invoke calls repository`(): Unit = runBlocking {
        val movie = sampleMovie(1)
        val repository = mock<MoviesRepository>()
        val useCase =  ToggleFavoriteUseCase(repository)

        useCase(movie)

        verify(repository).toggleFavoriteMovie(movie)
    }
}
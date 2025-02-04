package com.capgemini.architectcoders.domain.movie.di

import com.capgemini.architectcoders.domain.movie.data.MoviesRepository
import com.capgemini.architectcoders.domain.movie.usecases.FetchMoviesUseCase
import com.capgemini.architectcoders.domain.movie.usecases.FindMovieByIdUseCase
import com.capgemini.architectcoders.domain.movie.usecases.ToggleFavoriteUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

/*@Module
@ComponentScan
class DomainMovieModule*/

val domainMovieModule = module{
    factoryOf(::MoviesRepository)
    factoryOf(::FetchMoviesUseCase)
    factoryOf(::FindMovieByIdUseCase)
    factoryOf(::ToggleFavoriteUseCase)
}
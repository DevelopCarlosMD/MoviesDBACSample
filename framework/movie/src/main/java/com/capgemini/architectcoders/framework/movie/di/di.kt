package com.capgemini.architectcoders.framework.movie.di


import com.capgemini.architectcoders.domain.movie.data.MoviesLocalDataSource
import com.capgemini.architectcoders.domain.movie.data.MoviesRemoteDataSource
import com.capgemini.architectcoders.framework.movie.database.MoviesRoomDataSource
import com.capgemini.architectcoders.framework.movie.network.MoviesServerDataSource
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

/*@Module
@ComponentScan
class FrameworkMovieModule*/

val frameworkMovieModule = module {
    factoryOf(::MoviesRoomDataSource) bind MoviesLocalDataSource::class
    factoryOf(::MoviesServerDataSource) bind MoviesRemoteDataSource::class
}
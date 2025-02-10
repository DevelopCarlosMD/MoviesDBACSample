package com.capgemini.architectcoders.framework.movie.di

import com.capgemini.architectcoders.domain.movie.data.MoviesLocalDataSource
import com.capgemini.architectcoders.domain.movie.data.MoviesRemoteDataSource
import com.capgemini.architectcoders.framework.movie.database.MoviesRoomDataSource
import com.capgemini.architectcoders.framework.movie.network.MoviesServerDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FrameworkMovieModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSource: MoviesRoomDataSource): MoviesLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: MoviesServerDataSource): MoviesRemoteDataSource
}

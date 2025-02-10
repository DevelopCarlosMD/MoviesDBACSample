package com.capgemini.architectcoders.framework.core.di

import android.app.Application
import androidx.room.Room
import com.capgemini.architectcoders.framework.core.MoviesClient
import com.capgemini.architectcoders.framework.core.MoviesDatabase
import com.capgemini.architectcoders.framework.movie.network.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FrameworkCoreModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(app, MoviesDatabase::class.java, "movies-db").build()

    @Provides
    fun providesMoviesDao(database: MoviesDatabase) = database.moviesDao()

    @Provides
    @Singleton
    fun providesMoviesService(@Named("apiKey") apiKey: String): MoviesService = MoviesClient(apiKey).instance
}
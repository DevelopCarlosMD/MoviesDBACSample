package com.capgemini.architectcoders.framework.core

import android.app.Application
import androidx.room.Room
import com.capgemini.architectcoders.framework.movie.database.MoviesDao
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
    fun provideMoviesDao(db: MoviesDatabase) : MoviesDao = db.moviesDao()

    @Provides
    @Singleton
    fun provideMoviesService(
        @Named("apiKey") apiKey: String,
        @Named("apiUrl") apiUrl: String
    ): MoviesService = MoviesClient(apiKey, apiUrl).instance
}

@Module
@InstallIn(SingletonComponent::class)
object FrameworkCoreExtrasModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        MoviesDatabase::class.java,
        "movie-db"
    ).build()

    @Provides
    @Singleton
    @Named("apiUrl")
    fun provideApiUrl(): String = "https://api.themoviedb.org/3/"
}
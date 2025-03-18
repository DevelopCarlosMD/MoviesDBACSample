package com.capgemini.architectcoders.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.capgemini.architectcoders.ui.common.NavArgs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class DetailViewModelModule {
    @Provides
    @ViewModelScoped
    @Named("movieId")
    fun provideMovieId(savedStateHandle: SavedStateHandle) : Int {
        return savedStateHandle[NavArgs.MovieId.key] ?: -1
    }
}
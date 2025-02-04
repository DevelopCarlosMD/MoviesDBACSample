package com.capgemini.architectcoders.framework.core.di

import androidx.room.Room
import com.capgemini.architectcoders.framework.core.MoviesClient
import com.capgemini.architectcoders.framework.core.MoviesDatabase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val frameworkCoreModule = module {
    single { Room.databaseBuilder(get(), MoviesDatabase::class.java, "movies-db").build() }
    factory { get<MoviesDatabase>().moviesDao() }
    single { MoviesClient(get(named("apiKey"))).instance }
}
package com.capgemini.architectcoders.framework.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.capgemini.architectcoders.framework.movie.database.MovieEntity
import com.capgemini.architectcoders.framework.movie.database.MoviesDao


@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}


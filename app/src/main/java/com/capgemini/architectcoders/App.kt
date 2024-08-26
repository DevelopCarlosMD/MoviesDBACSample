package com.capgemini.architectcoders

import android.app.Application
import androidx.room.Room
import com.capgemini.architectcoders.data.datasource.database.MoviesDatabase

class App : Application() {
    lateinit var db: MoviesDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, MoviesDatabase::class.java, "movies-db").build()
    }
}
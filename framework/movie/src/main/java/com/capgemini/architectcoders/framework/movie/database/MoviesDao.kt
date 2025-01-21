package com.capgemini.architectcoders.framework.movie.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM MovieEntity")
    fun fetchPopularMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    fun findMovieById(id: Int): Flow<MovieEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<MovieEntity>)

    @Query("SELECT COUNT(*) FROM MovieEntity")
    suspend fun countMovies(): Int
}
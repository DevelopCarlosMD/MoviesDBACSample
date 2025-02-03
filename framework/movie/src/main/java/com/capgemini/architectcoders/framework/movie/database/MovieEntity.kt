package com.capgemini.architectcoders.framework.movie.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val poster: String,
    val backdrop: String?,
    val originalTitle: String,
    val originalLanguage: String,
    val popularity: Double,
    val voteAverage: Double,
    val isFavorite: Boolean
)

package com.capgemini.architectcoders.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val poster: String,
    val backdrop: String?,
    val originalTitle: String,
    val originalLanguage: String,
    val popularity: Double,
    val voteAverage: Double,
    val favorite: Boolean
)

/*data class Movie (
    val id: Int,
    val title: String,
    val poster: String
)*/

/*val movies = (1..100).map {
    Movie(
        id = it,
        title = "Movie $it",
        poster = "https://picsum.photos/200/300?id=$it"
    )
}*/
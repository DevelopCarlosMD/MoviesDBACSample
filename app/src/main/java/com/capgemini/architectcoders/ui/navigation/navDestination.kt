package com.capgemini.architectcoders.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Details(val movieId: Int)
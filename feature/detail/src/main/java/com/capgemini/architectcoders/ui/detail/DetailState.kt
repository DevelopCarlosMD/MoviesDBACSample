@file:OptIn(ExperimentalMaterial3Api::class)

package com.capgemini.architectcoders.ui.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.capgemini.architectcoders.domain.movie.entities.Movie
import com.capgemini.architectcoders.ui.common.Result


@OptIn(ExperimentalMaterial3Api::class)
class DetailState(
    private val state: Result<Movie>,
    val scrollBehavior: TopAppBarScrollBehavior,
    val snackBarHostState: SnackbarHostState
) {

    val movie: Movie?
        get() = (state as? Result.Success)?.data

    val topBarTitle: String
        get() = movie?.title ?: ""

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberDetailState(
    state: Result<Movie>,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() }
): DetailState {
    return remember(state) {
        DetailState(state, scrollBehavior, snackBarHostState)
    }
}
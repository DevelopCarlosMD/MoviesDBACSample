package com.capgemini.architectcoders.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.capgemini.architectcoders.ui.detail.DetailScreen
import com.capgemini.architectcoders.ui.home.HomeScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {// Destino de origen
        composable<Home> {
            HomeScreen(
                onMovieClick = { movie ->
                    navController.navigate(Details(movie.id))
                }
            )
        }
        composable<Details> { backStackEntry ->
            val details = backStackEntry.toRoute<Details>()
            DetailScreen(
                koinViewModel(parameters = { parametersOf(details.movieId) }),
                onBack = { navController.popBackStack() })
        }
    }
}
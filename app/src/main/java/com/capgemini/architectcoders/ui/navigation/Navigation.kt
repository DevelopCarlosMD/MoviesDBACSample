package com.capgemini.architectcoders.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.capgemini.architectcoders.ui.screens.detail.DetailScreen
import com.capgemini.architectcoders.ui.screens.detail.DetailViewModel
import com.capgemini.architectcoders.ui.screens.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home) {// Destino de origen
        composable<Home> {
            HomeScreen(onMovieClick = { movie ->
                navController.navigate(Details(movie.id))
            })
        }
        composable<Details>{ backStackEntry ->
            val details = backStackEntry.toRoute<Details>()
            DetailScreen(
                //viewModel = movies.first { it.id == details.movieId },
                viewModel { DetailViewModel(details.movieId) },
                onBack = { navController.popBackStack() })
        }
    }
    /*NavHost(navController = navController, startDestination = "home") {// Destino de origen
        composable("home") {
            HomeScreen(onMovieClick = { movie ->
                navController.navigate("detail/${movie.id}")
            })
        }
        composable(
            route = "detail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = requireNotNull(backStackEntry.arguments?.getInt("movieId"))
            DetailScreen(
                movie = movies.first { it.id == movieId },
                onBack = { navController.popBackStack() })
        }
    }*/
}
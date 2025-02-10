package com.capgemini.architectcoders.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.capgemini.architectcoders.ui.common.NavArgs
import com.capgemini.architectcoders.ui.common.NavScreen
import com.capgemini.architectcoders.ui.detail.DetailScreen
import com.capgemini.architectcoders.ui.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavScreen.Home.route) {// Destino de origen
        composable(NavScreen.Home.route) {
            HomeScreen(onMovieClick = { movie ->
                navController.navigate(NavScreen.Detail.createRoute(movie.id))
            })
        }
        composable(
            route = NavScreen.Detail.route,
            arguments = listOf(navArgument(NavArgs.MovieId.key) { type = NavType.IntType })
        ) {backStackEntry ->
            val movieId = requireNotNull(backStackEntry.arguments?.getInt(NavArgs.MovieId.key))
            DetailScreen(
                onBack = { navController.popBackStack() })
        }
    }
}
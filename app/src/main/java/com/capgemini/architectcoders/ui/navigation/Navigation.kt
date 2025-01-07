package com.capgemini.architectcoders.ui.navigation

import android.location.Geocoder
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.capgemini.architectcoders.App
import com.capgemini.architectcoders.data.MoviesRepository
import com.capgemini.architectcoders.data.RegionRepository
import com.capgemini.architectcoders.framework.GeoCoderRegionDataSource
import com.capgemini.architectcoders.framework.MoviesRoomDataSource
import com.capgemini.architectcoders.framework.MoviesServerDataSource
import com.capgemini.architectcoders.framework.PlayServicesLocationDataSource
import com.capgemini.architectcoders.framework.remote.MoviesClient
import com.capgemini.architectcoders.ui.screens.detail.DetailScreen
import com.capgemini.architectcoders.ui.screens.detail.DetailViewModel
import com.capgemini.architectcoders.ui.screens.home.HomeScreen
import com.capgemini.architectcoders.ui.screens.home.HomeViewModel
import com.capgemini.architectcoders.usecases.FetchMoviesUseCase
import com.capgemini.architectcoders.usecases.FindMovieByIdUseCase
import com.capgemini.architectcoders.usecases.ToggleFavoriteUseCase
import com.google.android.gms.location.LocationServices

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val app = LocalContext.current.applicationContext as App
    val moviesRepository =
        MoviesRepository(
            RegionRepository(
                GeoCoderRegionDataSource(
                    Geocoder(app),
                    PlayServicesLocationDataSource(
                        LocationServices.getFusedLocationProviderClient(app)
                    )
                )
            ),
            MoviesRoomDataSource(app.db.moviesDao()),
            MoviesServerDataSource(MoviesClient.instance)
        )
    NavHost(navController = navController, startDestination = Home) {// Destino de origen
        composable<Home> {
            HomeScreen(onMovieClick = { movie ->
                navController.navigate(Details(movie.id))
            }, viewModel { HomeViewModel(FetchMoviesUseCase(moviesRepository)) })
        }
        composable<Details> { backStackEntry ->
            val details = backStackEntry.toRoute<Details>()
            DetailScreen(
                //viewModel = movies.first { it.id == details.movieId },
                viewModel {
                    DetailViewModel(
                        details.movieId,
                        FindMovieByIdUseCase(moviesRepository),
                        ToggleFavoriteUseCase(moviesRepository)
                    )
                },
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

    /*
    * sealed class NavScreen(val route: String) {
        object Home : NavScreen("home")
        data class Detail : NavScreen("detail/{${NavArgs.MovieId.key}}") {
            fun createRoute(movieId: Int) = "detail/$movieId"
        }
    }
    *
    * enum class NavArgs(val key: String) {
        MovieId("movieId")
    }
    *
    * NavHost(navController = navController, startDestination = NavScreen.Home.route) {// Destino de origen
        composable(NavScreen.Home.route) {
            HomeScreen(onMovieClick = { movie ->
                navController.navigate(NavScreen.Detail.createRoute(movie.id))
            })
        }
        composable(
            route = NavScreen.Detail.route,
            arguments = listOf(NaArgs.MovieId.key) { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = requireNotNull(backStackEntry.arguments?.getInt(NavArgs.MovieId.key))
            DetailScreen(
                viewModel = { DetailViewModel(movieId) },
                onBack = { navController.popBackStack() })
        }
    }
    * */
}
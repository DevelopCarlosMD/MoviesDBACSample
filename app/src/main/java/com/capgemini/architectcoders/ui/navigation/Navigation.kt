package com.capgemini.architectcoders.ui.navigation

import android.location.Geocoder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.capgemini.architectcoders.App
import com.capgemini.architectcoders.BuildConfig
import com.capgemini.architectcoders.domain.movie.data.MoviesRepository
import com.capgemini.architectcoders.domain.movie.usecases.FindMovieByIdUseCase
import com.capgemini.architectcoders.domain.movie.usecases.ToggleFavoriteUseCase
import com.capgemini.architectcoders.domain.region.data.RegionRepository
import com.capgemini.architectcoders.framework.core.MoviesClient
import com.capgemini.architectcoders.framework.movie.database.MoviesRoomDataSource
import com.capgemini.architectcoders.framework.movie.network.MoviesServerDataSource
import com.capgemini.architectcoders.framework.region.GeocoderRegionDataSource
import com.capgemini.architectcoders.framework.region.PlayServicesLocationDataSource
import com.capgemini.architectcoders.ui.detail.DetailScreen
import com.capgemini.architectcoders.ui.detail.DetailViewModel
import com.capgemini.architectcoders.ui.home.HomeScreen
import com.capgemini.architectcoders.ui.home.HomeViewModel


import com.google.android.gms.location.LocationServices

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val app = LocalContext.current.applicationContext as App
    val moviesRepository = remember {
        MoviesRepository(
            regionRepository = RegionRepository(
                GeocoderRegionDataSource(
                    Geocoder(app),
                    PlayServicesLocationDataSource(
                        LocationServices.getFusedLocationProviderClient(app)
                    )
                )
            ),
            localDataSource = MoviesRoomDataSource(app.db.moviesDao()),
            remoteDataSource = MoviesServerDataSource(
                MoviesClient(
                    BuildConfig.TMDB_API_KEY
                ).instance
            )
        )
    }
    NavHost(navController = navController, startDestination = Home) {// Destino de origen
        composable<Home> {
            HomeScreen(onMovieClick = { movie ->
                navController.navigate(Details(movie.id))
            }, viewModel { HomeViewModel(moviesRepository) })
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
}
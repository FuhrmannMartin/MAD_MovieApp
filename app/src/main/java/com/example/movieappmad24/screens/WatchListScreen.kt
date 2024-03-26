package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.movieappmad24.MovieList
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import com.example.movieappmad24.ui.theme.SimpleBottomAppBar
import com.example.movieappmad24.ui.theme.SimpleTopAppBar
import com.example.movieappmad24.viewmodels.MoviesViewModel

@Composable
fun WatchListScreen(navController: NavHostController, movieViewModel: MoviesViewModel) {
    val movies: List<Movie> = movieViewModel.favoriteMovieList

    MovieAppMAD24Theme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            // color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = { SimpleTopAppBar("Watchlist", navController) },
                bottomBar = { SimpleBottomAppBar(navController) }
            ) { innerPadding ->
                MovieList(
                    movies = movies,
                    innerPadding = innerPadding,
                    navController = navController,
                    movieViewModel = movieViewModel)
            }
        }
    }
}

package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMoviesById
import androidx.compose.ui.Modifier
import com.example.movieappmad24.MovieLazyRow
import com.example.movieappmad24.MovieRow
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import com.example.movieappmad24.ui.theme.SimpleTopAppBar

@Composable
fun DetailScreen(movieId: String, navController: NavController) {
    val movie =  getMoviesById(movieId)
    if (movie != null) {
        MovieAppMAD24Theme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                // color = MaterialTheme.colorScheme.background
            ) {
                Scaffold(
                    topBar = { SimpleTopAppBar(movie.title, navController) }
                ) { innerPadding ->
                    ShowDetailScreen(movie, innerPadding)
                }
            }
        }
    }
}

@Composable
fun ShowDetailScreen(movie: Movie, innerPadding: PaddingValues) {
    Column(modifier = Modifier.padding(innerPadding)) {
        MovieRow(movie, onItemClick = {})
        MovieLazyRow(movie)
    }
}
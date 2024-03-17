package com.example.movieappmad24.screens
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.movieappmad24.MovieList
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import com.example.movieappmad24.ui.theme.SimpleBottomAppBar
import com.example.movieappmad24.ui.theme.SimpleTopAppBar

@Composable
fun HomeScreen(navController: NavHostController) {
    MovieAppMAD24Theme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            // color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = { SimpleTopAppBar("Movie App", navController) },
                bottomBar = { SimpleBottomAppBar(navController) }
            ) { innerPadding ->
                MovieList(getMovies(), innerPadding, navController)
            }
        }
    }
}
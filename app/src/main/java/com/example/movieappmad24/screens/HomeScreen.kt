package com.example.movieappmad24.screens
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.movieappmad24.MovieList
import com.example.movieappmad24.di.InjectorUtils
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import com.example.movieappmad24.ui.theme.SimpleBottomAppBar
import com.example.movieappmad24.ui.theme.SimpleTopAppBar
import com.example.movieappmad24.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeScreenViewModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(
        LocalContext.current))
    val moviesState = viewModel.movies.collectAsState()


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
                MovieList(
                    moviesWithImages = moviesState.value,
                    innerPadding = innerPadding,
                    navController = navController,
                    viewModel = viewModel)
            }
        }
    }
}
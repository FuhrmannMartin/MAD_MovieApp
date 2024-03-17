package com.example.movieappmad24.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad24.screens.DetailScreen
import com.example.movieappmad24.screens.HomeScreen
import com.example.movieappmad24.screens.WatchListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController() // create a NavController instance
    NavHost(navController = navController, // pass the NavController to NavHost
        startDestination = Screen.Home.route
    ) { // pass a start destination
        composable(
            route = Screen.Home.route,
        ){
            HomeScreen(navController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(name = MOVIE_ID_KEY) {type = NavType.StringType})
        ){
            backStackEntry ->
            backStackEntry.arguments?.getString(MOVIE_ID_KEY)?.let { DetailScreen(movieId = it, navController) }
        }
        composable(
            route = Screen.WatchList.route,
        ){
            WatchListScreen(navController)
        }
    }
}
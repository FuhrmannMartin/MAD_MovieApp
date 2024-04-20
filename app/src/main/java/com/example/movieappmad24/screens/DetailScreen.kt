package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.movieappmad24.MovieLazyRow
import com.example.movieappmad24.MovieRow
import com.example.movieappmad24.di.InjectorUtils
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import com.example.movieappmad24.ui.theme.SimpleTopAppBar
import com.example.movieappmad24.viewmodels.DetailScreenViewModel

@Composable
fun DetailScreen(id: String, navController: NavController) {
    val viewModel: DetailScreenViewModel = viewModel(factory = InjectorUtils.provideMovieViewModelFactory(
        LocalContext.current))
    val movieWithImages by viewModel.getMovieById(id).collectAsState(initial = null)

    movieWithImages?.let { movie ->
        MovieAppMAD24Theme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                // color = MaterialTheme.colorScheme.background
            ) {
                Scaffold(
                    topBar = { SimpleTopAppBar(movie.movie.title, navController) }
                ) { innerPadding ->
                    ShowDetailScreen(movie, viewModel, innerPadding)
                }
            }
        }
    }
}

@Composable
fun ShowDetailScreen(movieWithImages: MovieWithImages, viewModel: DetailScreenViewModel, innerPadding: PaddingValues) {
    Column(modifier = Modifier.padding(innerPadding)) {
        MovieRow(
            movieWithImages = movieWithImages,
            onMovieRowClick = {},
            onFavClick = {id -> viewModel.toggleIsFavorite(id)}
        )
        Player(movieWithImages.movie.trailer)
        MovieLazyRow(movieWithImages)
    }
}

@Composable
fun Player(trailer: String) {
    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }

    val context = LocalContext.current
    val mediaItem = MediaItem.fromUri(
        "android.resource://${context.packageName}/raw/$trailer"
    )
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    val lifeCycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifeCycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifeCycleOwner.lifecycle.addObserver(observer)

        onDispose {
            exoPlayer.release()
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(18f / 9f),
        factory = {
            PlayerView(context).also {playerView ->
                playerView.player = exoPlayer
            }
        },
        update = {
            when(lifecycle) {
                Lifecycle.Event.ON_STOP -> {
                    exoPlayer.stop()
                }
                Lifecycle.Event.ON_RESUME -> {
                    exoPlayer.play()
                }
                else -> Unit
            }
        }
    )

}
package com.example.movieappmad24

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.navigation.Screen
import com.example.movieappmad24.viewmodels.FavoriteToggleViewModelInterface

@Composable
fun <T> MovieList(moviesWithImages: List<MovieWithImages>, innerPadding: PaddingValues, navController: NavHostController, viewModel: T
    ) where T : ViewModel, T : FavoriteToggleViewModelInterface {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(moviesWithImages) { movieWithImages ->
            MovieRow(
                movieWithImages = movieWithImages,
                onMovieRowClick = {id -> navController.navigate(route = Screen.Detail.passId(id))},
                onFavClick = { viewModel.toggleIsFavorite(movieWithImages) }
            )
        }
    }
}

@Composable
fun MovieRow(movieWithImages: MovieWithImages, onMovieRowClick: (String) -> Unit = {}, onFavClick: (MovieWithImages) -> Boolean){
    var showDetails by remember {
        mutableStateOf(false)
    }
    var isFavorite by remember {
        mutableStateOf(movieWithImages.movie.isFavorite)
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .clickable {
            onMovieRowClick(movieWithImages.movie.id)
        },
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                // Log.d("Tag", movie.images.first())
                AsyncImage(
                    model = movieWithImages.imageUrls.first(),
                    contentDescription = "null",
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ){
                    Icon(modifier = Modifier
                        .clickable {
                            isFavorite = onFavClick(movieWithImages)
                        },
                        tint = MaterialTheme.colorScheme.secondary,
                        imageVector =
                        if (isFavorite) Icons.Default.Favorite
                        else Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = movieWithImages.movie.title, style = TextStyle(fontWeight = FontWeight.Bold))
                Icon(modifier = Modifier
                    .clickable {
                        showDetails = !showDetails
                    },
                    imageVector =
                    if (showDetails) Icons.Filled.KeyboardArrowDown
                    else Icons.Default.KeyboardArrowUp,
                    contentDescription = "show more")
            }
            if (showDetails) {
                AnimatedVisibility(visible = true, enter = fadeIn() + expandVertically()) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "Director: ${movieWithImages.movie.director}")
                        Text(text = "Released: ${movieWithImages.movie.year}")
                        Text(text = "Genre: ${movieWithImages.movie.genre}")
                        Text(text = "Actors: ${movieWithImages.movie.actors}")
                        Text(text = "Rating: ${movieWithImages.movie.rating}")
                        Divider(color = Color.LightGray, thickness = 1.dp)
                        Text(text = "Plot: ${movieWithImages.movie.plot}")
                    }
                }
            }
        }
    }
}

@Composable
fun MovieLazyRow(movieWithImages: MovieWithImages){
    LazyRow (
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(movieWithImages.imageUrls) { index, imageUrl ->
            Card(modifier = Modifier.padding(8.dp)) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = index.toString(),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(120.dp)
                )
            }
        }
    }
}
package com.example.movieappmad24.viewmodels

import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.repositories.MovieRepository

interface FavoriteToggleViewModelInterface {

    val repository: MovieRepository

    suspend fun toggleIsFavorite(movieWithImages: MovieWithImages) {
        val newFavoriteState = !movieWithImages.movie.isFavorite
        movieWithImages.movie.isFavorite = newFavoriteState
        repository.updateMovie(movieWithImages.movie)
    }

}

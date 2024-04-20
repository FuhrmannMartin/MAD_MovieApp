package com.example.movieappmad24.viewmodels

import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.repositories.MovieRepository

interface FavoriteToggleViewModelInterface {
    val repository: MovieRepository
    fun toggleIsFavorite(movieWithImages: MovieWithImages): Boolean {
        movieWithImages.movie.isFavorite = !movieWithImages.movie.isFavorite
        repository.updateMovie(movieWithImages.movie)
        return movieWithImages.movie.isFavorite
    }
}

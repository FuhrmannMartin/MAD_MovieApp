package com.example.movieappmad24.viewmodels

import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface FavoriteToggleViewModelInterface {

    val repository: MovieRepository
    val movies: Flow<List<MovieWithImages>>
        get() = flow { emit(emptyList()) } // Default value

    suspend fun toggleIsFavorite(movieWithImages: MovieWithImages) {
        val newFavoriteState = !movieWithImages.movie.isFavorite
        movieWithImages.movie.isFavorite = newFavoriteState
        repository.updateMovie(movieWithImages.movie)
    }

}

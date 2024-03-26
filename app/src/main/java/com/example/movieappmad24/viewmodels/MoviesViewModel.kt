package com.example.movieappmad24.viewmodels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies

class MoviesViewModel: ViewModel() {
    private val _movieList = getMovies().toMutableStateList()
    val movieList: List<Movie>
        get() = _movieList

    val favoriteMovieList: List<Movie>
        get() = _movieList.filter { it.isFavorite }

    fun toggleIsFavorite(id: String): Boolean {
        val movie = _movieList.find { it.id == id }

        if (movie != null) {
            movie.isFavorite = !movie.isFavorite
            return movie.isFavorite
        }

        return false
    }

    fun getMovieById(id: String): Movie? {
        return _movieList.find { it.id == id }
    }

}
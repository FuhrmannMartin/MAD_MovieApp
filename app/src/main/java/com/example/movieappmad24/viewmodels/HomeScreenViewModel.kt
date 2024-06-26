package com.example.movieappmad24.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.repositories.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(override val repository: MovieRepository): ViewModel(), FavoriteToggleViewModelInterface {
    private val _movies = MutableStateFlow<List<MovieWithImages>>(emptyList())
    val movies: StateFlow<List<MovieWithImages>> = _movies

    private val moviesJob = viewModelScope.launch {
            repository.getAllMovies().collect { movieList ->
                _movies.value = movieList
                Log.d("_Flow", "HomeScreenViewModel emitting $movieList")
            }
        }

    fun clear() {
        moviesJob.cancel()
    }

}
package com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow

class DetailScreenViewModel(override val repository: MovieRepository): ViewModel(), FavoriteToggleViewModelInterface {
    fun getMovieById(id: String): Flow<MovieWithImages?> {
        return repository.getMovieById(id)
    }
}
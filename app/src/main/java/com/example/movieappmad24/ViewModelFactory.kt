package com.example.movieappmad24

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad24.repositories.MovieRepository
import com.example.movieappmad24.viewmodels.DetailScreenViewModel
import com.example.movieappmad24.viewmodels.HomeScreenViewModel
import com.example.movieappmad24.viewmodels.WatchListScreenViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailScreenViewModel::class.java) -> {
                DetailScreenViewModel(repository = repository) as T
            }

            modelClass.isAssignableFrom(HomeScreenViewModel::class.java) -> {
                HomeScreenViewModel(repository = repository) as T
            }

            modelClass.isAssignableFrom(WatchListScreenViewModel::class.java) -> {
                WatchListScreenViewModel(repository = repository) as T
            }

            else -> throw IllegalArgumentException("Unknown VieModel class")
        }
    }
}
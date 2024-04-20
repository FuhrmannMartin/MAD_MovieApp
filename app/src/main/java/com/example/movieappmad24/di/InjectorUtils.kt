package com.example.movieappmad24.di

import android.content.Context
import com.example.movieappmad24.ViewModelFactory
import com.example.movieappmad24.data.MovieDatabase
import com.example.movieappmad24.repositories.MovieRepository

object InjectorUtils {

    private fun getMovieRepository(context: Context): MovieRepository {
        val database = MovieDatabase.getDatabase(context.applicationContext)
        val movieDao = database.movieDao()
        val movieImageDao = database.movieImageDao()
        return MovieRepository.getInstance(movieDao, movieImageDao)
    }

    fun provideMovieViewModelFactory(context: Context): ViewModelFactory {
        val repository = getMovieRepository(context)
        return ViewModelFactory(repository = repository)
    }

}
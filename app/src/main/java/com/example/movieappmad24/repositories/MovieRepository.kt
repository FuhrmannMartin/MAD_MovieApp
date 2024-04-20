package com.example.movieappmad24.repositories

import com.example.movieappmad24.data.MovieDao
import com.example.movieappmad24.data.MovieImageDao
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieWithImages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(private val movieDao: MovieDao, private val movieImageDao: MovieImageDao) {
    fun insertMovie(movie: Movie) = movieDao.insert(movie)
    fun deleteMovie(movie: Movie) = movieDao.delete(movie)
    fun updateMovie(movie: Movie) = movieDao.update(movie)
    fun getAllMovies(): Flow<List<MovieWithImages>> {
        return movieDao.getAll().map { movies ->
            movies.map { movie ->
                val imageUrls = movieImageDao.getUrls(movie.id)
                MovieWithImages(movie, imageUrls)
            }
        }
    }
    fun getFavoriteMovies(): Flow<List<MovieWithImages>> {
        return movieDao.getFavorite().map { movies ->
            movies.map { movie ->
                val imageUrls = movieImageDao.getUrls(movie.id)
                MovieWithImages(movie, imageUrls)
            }
        }
    }
    fun getMovieById(id: String): Flow<MovieWithImages?> {
        return movieDao.getMovieById(id).map { movie ->
            movie?.let {
                val imageUrls = movieImageDao.getUrls(it.id)
                MovieWithImages(it, imageUrls)
            }
        }
    }

    companion object {
        @Volatile
        private var Instance: MovieRepository ?= null
        fun getInstance(movieDao: MovieDao, movieImageDao: MovieImageDao) = Instance ?: synchronized(this) {
            Instance ?: MovieRepository(movieDao, movieImageDao).also { Instance = it }
        }
    }
}
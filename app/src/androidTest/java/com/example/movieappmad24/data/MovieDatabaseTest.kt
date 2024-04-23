package com.example.movieappmad24.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.movieappmad24.models.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieDatabaseTest {

    private lateinit var database: MovieDatabase
    private lateinit var movieDao: MovieDao
    private lateinit var movieImageDao: MovieImageDao
    private val context: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setUp() {
        database = MovieDatabase.getDatabase(context)
        movieDao = database.movieDao()
        movieImageDao = database.movieImageDao()
    }

    @Test
    fun testGetAllMovies() = runBlocking {
        val movies = MutableStateFlow(listOf<Movie>())

        movieDao.getAll().take(1).collect {movieList ->
            movies.value = movieList
        }

        delay(30000)

        Assert.assertTrue("Number of collected movies from database is ${movies.value.size}", movies.value.size == 9)
    }

    @Test
    fun getMovieById() = runBlocking {

        val movie = movieDao.getMovieById("tt0816692").firstOrNull()

        if (movie != null) {
            Assert.assertTrue("Movie Interstellar was found by id", movie.title == "Interstellar")
        }
    }

}
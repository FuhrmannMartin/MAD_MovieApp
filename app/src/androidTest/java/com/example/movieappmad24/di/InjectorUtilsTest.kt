package com.example.movieappmad24.di

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.repositories.MovieRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InjectorUtilsTest {

    private lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        repository = InjectorUtils.getMovieRepository(context)
    }

    @Test
    fun getAllMovies() = runBlocking {
        val movies = MutableStateFlow(listOf<MovieWithImages>())

        repository.getAllMovies().take(1).collect {movieList ->
            movies.value = movieList
        }

        delay(30000)

        Assert.assertTrue("Number of collected movies from database is ${movies.value.size}", movies.value.size == 9)
    }
}

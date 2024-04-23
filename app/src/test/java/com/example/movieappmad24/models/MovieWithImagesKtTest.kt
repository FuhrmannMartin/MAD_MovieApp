package com.example.movieappmad24.models

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieWithImagesKtTest {

    lateinit var movieWithImages: List<MovieWithImages>

    @Before
    fun setUp() {
        movieWithImages = getMoviesWithImages()
    }

    @Test
    fun checkLength() {
        Assert.assertEquals(9, movieWithImages.size)
    }

    @Test
    fun checkFirstTitle() {
        Assert.assertEquals("Avatar", movieWithImages.first().movie.title)
    }
}
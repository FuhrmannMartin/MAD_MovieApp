package com.example.movieappmad24.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.movieappmad24.models.MovieImage
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieImageDao {
    @Insert
    suspend fun insert(movieImage: MovieImage)

    @Delete
    suspend fun delete(movieImage: MovieImage)

    @Update
    suspend fun update(movieImage: MovieImage)

    @Query("SELECT url FROM movieImage WHERE id = :id")
    fun getUrls(id: String): Flow<List<String>>

    @Query("SELECT url FROM movieImage")
    fun getAllUrls(): Flow<List<String>>

}
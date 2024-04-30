package com.example.movieappmad24.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Movie::class,
        parentColumns = ["id"],
        childColumns = ["id"],
        onDelete = CASCADE // Cascade deletion if Movie is deleted
    )]
)
class MovieImage(
    @PrimaryKey(autoGenerate = true) var dbId: Long = 0,
    val id: String,
    val url: String
)


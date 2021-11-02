package com.venicio.sagamarvel.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "movie_table")
 data class Movies(

    val actors: String,
    val director: String,
    val genre: String,
    val plot: String,
    val poster: String,
    val rated: String,
    val released: String,
    val runtime: String,
    @PrimaryKey
    val title: String,
    val writer: String,
    val year: String
) : Parcelable



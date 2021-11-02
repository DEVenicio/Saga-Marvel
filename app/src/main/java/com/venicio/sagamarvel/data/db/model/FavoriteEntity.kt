package com.venicio.sagamarvel.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.venicio.sagamarvel.data.model.Movies

@Entity(tableName = "table_favorites")
data class FavoriteEntity(

    val actors: String,
    val director: String,
    val genre: String,
    val plot: String,
    val poster: String,
    val rated: String,
    val released: String,
    val runtime: String,
    @PrimaryKey()
    val title: String,
    val writer: String,
    val year: String
)

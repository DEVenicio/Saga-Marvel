package com.venicio.sagamarvel.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.venicio.sagamarvel.data.model.Movies
import com.venicio.sagamarvel.data.model.toFavoriteEntity

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

    fun FavoriteEntity.toMovies (): Movies {
        return Movies(
            this.actors,this.director,this.genre,this.plot,this.poster,this.rated,this.released,this.runtime,this.title,this.writer,this.year
        )
    }

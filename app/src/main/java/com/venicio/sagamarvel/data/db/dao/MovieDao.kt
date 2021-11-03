package com.venicio.sagamarvel.data.db.dao

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.room.*
import com.venicio.sagamarvel.data.db.model.FavoriteEntity
import com.venicio.sagamarvel.data.model.Movies
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movies)

    @Query("SELECT * FROM movie_table")
    fun getAll(): Flow<List<Movies>>

    @Query("SELECT * FROM movie_table ORDER BY title ASC")
    fun filterByName(): LiveData<List<Movies>>

    @Query("SELECT * FROM movie_table ORDER BY year ASC")
    fun filterByYear(): LiveData<List<Movies>>

    @Query("SELECT * FROM movie_table WHERE title LIKE :searchQuery")
    fun searchFromDatabase(searchQuery: String): LiveData<List<Movies>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(movieFavorite: FavoriteEntity)

    @Query("SELECT * FROM table_favorites ORDER BY title ASC")
    fun getFavoriteAll(): Flow<List<FavoriteEntity>>

    @Query("DELETE FROM table_favorites WHERE title = :title")
    suspend fun removeFavorite(title: String)

}

package com.venicio.sagamarvel.data.db

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.venicio.sagamarvel.data.model.Movies

class Converters {


    private val moshi = Moshi.Builder().build()
    private val jsonAdapter = moshi.adapter(Movies::class.java)


    @TypeConverter
    fun moviesToString(movies: Movies): String {
        return jsonAdapter.toJson(movies)
    }


}

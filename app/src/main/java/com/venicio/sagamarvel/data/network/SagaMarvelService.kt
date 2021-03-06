package com.venicio.sagamarvel.data.network

import com.venicio.sagamarvel.data.model.Movies
import retrofit2.http.GET

interface SagaMarvelService {

    @GET("saga")
    suspend fun fetchAllMoviesApi() : List<Movies>

}
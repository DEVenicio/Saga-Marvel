package com.venicio.sagamarvel.data.model

import com.squareup.moshi.Json

data class MoviesResponse(
   @Json(name = "")
   val movies : List<Movies>
)

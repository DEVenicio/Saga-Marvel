package com.venicio.sagamarvel.data.repository

import com.venicio.sagamarvel.data.network.SagaMarvelClient

class SagaMarvelRepository {

    private val service = SagaMarvelClient.getInstance()

    suspend fun getAllMovies() = service.fetchAllMovies()
}
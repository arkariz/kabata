package com.arrkariz.kabata.data.network

import com.arrkariz.kabata.data.network.response.MovieResponse
import com.arrkariz.kabata.domain.model.TokenEntity
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("token/")
    suspend fun postToken(@Body token: TokenEntity)

    @GET("refresh_movie/")
    suspend fun getMovieList(): List<MovieResponse>

    @GET("newest_movie/")
    suspend fun getNewestMovie(): MovieResponse
}
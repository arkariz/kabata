package com.arrkariz.kabata.features.moviesexplore.data.network

import com.arrkariz.kabata.features.moviesexplore.data.network.response.MovieResponse
import com.arrkariz.kabata.features.moviesexplore.data.network.response.TokenResponse
import com.arrkariz.kabata.features.moviesexplore.domain.model.TokenEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("token/")
    suspend fun postToken(@Body token: TokenEntity): Response<TokenResponse>

    @GET("token/")
    suspend fun getToken(): Response<List<TokenResponse>>

    @GET("refresh_movie/")
    suspend fun getMovieList(): Response<List<MovieResponse>>

    @GET("newest_movie/")
    suspend fun getNewestMovie(): Response<MovieResponse>
}
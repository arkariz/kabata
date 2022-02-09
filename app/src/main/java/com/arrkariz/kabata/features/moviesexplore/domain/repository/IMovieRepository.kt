package com.arrkariz.kabata.features.moviesexplore.domain.repository

import com.arrkariz.kabata.features.moviesexplore.data.network.response.MovieResponse
import com.arrkariz.kabata.features.moviesexplore.data.network.response.TokenResponse
import com.arrkariz.kabata.features.moviesexplore.domain.model.TokenEntity
import retrofit2.Response

interface IMovieRepository {

    suspend fun postToken(token: TokenEntity): Response<TokenResponse>
    suspend fun getToken(): Response<List<TokenResponse>>
    suspend fun getMovieList(): Response<List<MovieResponse>>
    suspend fun getNewestMovie(): Response<MovieResponse>

}
package com.arrkariz.kabata.domain.repository

import com.arrkariz.kabata.data.network.response.MovieResponse
import com.arrkariz.kabata.data.network.response.TokenResponse
import com.arrkariz.kabata.domain.model.TokenEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IMovieRepository {

    suspend fun postToken(token: TokenEntity)
    suspend fun getToken(): Response<List<TokenResponse>>
    suspend fun getMovieList(): List<MovieResponse>
    suspend fun getNewestMovie(): MovieResponse

}
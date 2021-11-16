package com.arrkariz.kabata.domain.repository

import com.arrkariz.kabata.data.network.response.MovieResponse
import com.arrkariz.kabata.domain.model.TokenEntity

interface IMovieRepository {

    suspend fun postToken(token: TokenEntity)
    suspend fun getMovieList(): List<MovieResponse>
    suspend fun getNewestMovie(): MovieResponse
}
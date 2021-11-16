package com.arrkariz.kabata.domain.repository

import com.arrkariz.kabata.data.network.response.MovieListResponse
import com.arrkariz.kabata.domain.model.TokenEntity

interface IMovieRepository {

    suspend fun postToken(token: TokenEntity)
    suspend fun getMovieList(): List<MovieListResponse>
}
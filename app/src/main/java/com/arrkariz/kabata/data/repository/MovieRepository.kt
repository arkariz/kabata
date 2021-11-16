package com.arrkariz.kabata.data.repository

import com.arrkariz.kabata.data.network.ApiService
import com.arrkariz.kabata.data.network.response.MovieResponse
import com.arrkariz.kabata.domain.model.TokenEntity
import com.arrkariz.kabata.domain.repository.IMovieRepository

class MovieRepository(private val apiService: ApiService): IMovieRepository {

    override suspend fun postToken(token: TokenEntity) = apiService.postToken(token)

    override suspend fun getMovieList(): List<MovieResponse> {
        return apiService.getMovieList()
    }

    override suspend fun getNewestMovie(): MovieResponse {
        return apiService.getNewestMovie()
    }
}
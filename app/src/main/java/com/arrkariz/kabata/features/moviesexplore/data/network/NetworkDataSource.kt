package com.arrkariz.kabata.features.moviesexplore.data.network

import com.arrkariz.kabata.features.moviesexplore.data.network.response.MovieResponse
import com.arrkariz.kabata.features.moviesexplore.data.network.response.TokenResponse
import com.arrkariz.kabata.features.moviesexplore.domain.model.TokenEntity
import retrofit2.Response

class NetworkDataSource(
    private val apiService: ApiService
) {
    suspend fun postToken(token: TokenEntity) = apiService.postToken(token)

    suspend fun getToken(): Response<List<TokenResponse>> = apiService.getToken()

    suspend fun getMovieList(): Response<List<MovieResponse>> {
        return apiService.getMovieList()
    }

    suspend fun getNewestMovie(): Response<MovieResponse> {
        return apiService.getNewestMovie()
    }
}
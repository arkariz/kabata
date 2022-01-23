package com.arrkariz.kabata.features.moviesexplore.data.repository

import com.arrkariz.kabata.features.moviesexplore.data.network.ApiService
import com.arrkariz.kabata.features.moviesexplore.data.network.response.MovieResponse
import com.arrkariz.kabata.features.moviesexplore.data.network.response.TokenResponse
import com.arrkariz.kabata.features.moviesexplore.domain.model.TokenEntity
import com.arrkariz.kabata.features.moviesexplore.domain.repository.IMovieRepository
import retrofit2.Response

class MovieRepository(private val apiService: ApiService): IMovieRepository {

    override suspend fun postToken(token: TokenEntity) = apiService.postToken(token)

    override suspend fun getToken(): Response<List<TokenResponse>> = apiService.getToken()

    override suspend fun getMovieList(): Response<List<MovieResponse>> {
        return apiService.getMovieList()
    }

    override suspend fun getNewestMovie(): Response<MovieResponse> {
        return apiService.getNewestMovie()
    }
}
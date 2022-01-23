package com.arrkariz.kabata.features.moviesexplore.domain

import com.arrkariz.kabata.features.moviesexplore.data.network.response.MovieResponse
import com.arrkariz.kabata.features.moviesexplore.data.network.response.TokenResponse
import com.arrkariz.kabata.features.moviesexplore.domain.model.TokenEntity
import com.arrkariz.kabata.features.moviesexplore.domain.repository.IMovieRepository
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response

class FakeMovieRepository: IMovieRepository {
    override suspend fun postToken(token: TokenEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getToken(): Response<List<TokenResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieList(): Response<List<MovieResponse>> {
        val movie: MutableList<MovieResponse> = mutableListOf()
        movie.add(MovieResponse(0, "test", "test", "test", "test"))
        return Response.success(movie)
//        return Response.success(204, movie)
//        throw HttpException(
//            Response.error<List<MovieResponse>>
//                (400,
//                "{\"key\":[\"somestuff\"]}"
//                    .toResponseBody("application/json"
//                        .toMediaTypeOrNull()
//                    )
//            )
//        )
    }

    override suspend fun getNewestMovie(): Response<MovieResponse> {
        TODO("Not yet implemented")
    }
}
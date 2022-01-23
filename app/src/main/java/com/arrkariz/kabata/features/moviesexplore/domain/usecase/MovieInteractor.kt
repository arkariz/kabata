package com.arrkariz.kabata.features.moviesexplore.domain.usecase

import android.util.Log
import com.arrkariz.kabata.features.moviesexplore.data.network.response.toMovieEntity
import com.arrkariz.kabata.features.moviesexplore.data.network.response.toTokenEntity
import com.arrkariz.kabata.features.moviesexplore.domain.model.MovieEntity
import com.arrkariz.kabata.features.moviesexplore.domain.model.TokenEntity
import com.arrkariz.kabata.features.moviesexplore.domain.repository.IMovieRepository
import com.arrkariz.kabata.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {

    override suspend fun postToken(token: TokenEntity) = movieRepository.postToken(token)

    override suspend fun getToken(): List<TokenEntity> {
        try{
            val response = movieRepository.getToken()
            return if (response.isSuccessful){
                val responseCode = response.code()
                Log.d("TokenResponse", "Token Receive: $responseCode")
                if (response.body() != null){
                    response.body()!!.map { it.toTokenEntity() }
                } else {
                    Log.d("TokenResponse", "Response Empty")
                    emptyList()
                }
            } else{
                val responseCode = response.code()
                Log.d("TokenResponse", responseCode.toString())
                emptyList()
            }
        } catch (e: HttpException){
            return emptyList()
        } catch (e: IOException){
            return emptyList()
        }

    }

    override fun getMovieList(): Flow<Resources<List<MovieEntity>>> = flow {
        try {
            emit(Resources.Loading(message = "loading"))
            val movies = movieRepository.getMovieList()
            if (movies.code() == 204){
                emit(Resources.Empty("There is no new movie yet"))
            } else if (movies.code() == 200){
                emit(Resources.Success(movies.body()!!.map { it.toMovieEntity() }))
            }
        } catch (e: HttpException){
            emit(Resources.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException){
            emit(Resources.Error("Couldn't reach server"))
        }
    }

    override fun getNewestMovie(): Flow<Resources<MovieEntity>> = flow {
        try {
            emit(Resources.Loading())
            val movie = movieRepository.getNewestMovie()
            if (movie.code() == 204){
                emit(Resources.Error("There is no new movie yet"))
            } else {
                emit(Resources.Success(movie.body()!!.toMovieEntity()))
            }
        } catch (e: HttpException){
            emit(Resources.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException){
            emit(Resources.Error("Couldn't reach server"))
        }
    }
}
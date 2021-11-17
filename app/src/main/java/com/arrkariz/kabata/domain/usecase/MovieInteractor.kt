package com.arrkariz.kabata.domain.usecase

import android.util.Log
import com.arrkariz.kabata.data.network.response.toMovieEntity
import com.arrkariz.kabata.data.network.response.toTokenEntity
import com.arrkariz.kabata.domain.model.MovieEntity
import com.arrkariz.kabata.domain.model.TokenEntity
import com.arrkariz.kabata.domain.repository.IMovieRepository
import com.arrkariz.kabata.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {

    override suspend fun postToken(token: TokenEntity) = movieRepository.postToken(token)

    override suspend fun getToken(): List<TokenEntity> {
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
    }

    override fun getMovieList(): Flow<Resources<List<MovieEntity>>> = flow {
        try {
            emit(Resources.Loading())
            val movies = movieRepository.getMovieList().map { it.toMovieEntity() }
            emit(Resources.Success(movies))
        } catch (e: HttpException){
            emit(Resources.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException){
            emit(Resources.Error("Couldn't reach server"))
        }
    }

    override fun getNewestMovie(): Flow<Resources<MovieEntity>> = flow {
        try {
            emit(Resources.Loading())
            val movie = movieRepository.getNewestMovie().toMovieEntity()
            emit(Resources.Success(movie))
        } catch (e: HttpException){
            emit(Resources.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException){
            emit(Resources.Error("Couldn't reach server"))
        }
    }
}
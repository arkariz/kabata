package com.arrkariz.kabata.features.moviesexplore.domain.usecase

import android.util.Log
import com.arrkariz.kabata.features.moviesexplore.data.network.response.toTokenEntity
import com.arrkariz.kabata.features.moviesexplore.domain.model.TokenEntity
import com.arrkariz.kabata.features.moviesexplore.domain.repository.IMovieRepository
import retrofit2.HttpException
import java.io.IOException

class FcmInteractor (private val movieRepository: IMovieRepository) : FcmUseCase {

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
}
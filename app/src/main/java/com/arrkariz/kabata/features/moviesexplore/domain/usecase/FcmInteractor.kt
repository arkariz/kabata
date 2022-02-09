package com.arrkariz.kabata.features.moviesexplore.domain.usecase

import android.util.Log
import com.arrkariz.kabata.features.moviesexplore.data.network.response.toTokenEntity
import com.arrkariz.kabata.features.moviesexplore.domain.model.TokenEntity
import com.arrkariz.kabata.features.moviesexplore.domain.repository.IMovieRepository
import com.arrkariz.kabata.utils.Resources
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class FcmInteractor (private val movieRepository: IMovieRepository) : FcmUseCase {

    override suspend fun postToken(token: TokenEntity): Resources<TokenEntity> {
        try{
            val response = movieRepository.postToken(token)
            return if (response.isSuccessful){
                val responseCode = response.code()
                Log.d("TokenResponse", "Token Receive: $responseCode")
                if (response.body() != null){
                    val result = response.body()!!.toTokenEntity()
                    Resources.Success(result)
                } else {
                    Log.d("TokenResponse", "Response Empty")
                    Resources.Empty("Empty Token")
                }
            } else{
                val responseCode = response.code()
                Log.d("TokenResponse", responseCode.toString())
                throw Exception()
            }
        } catch (e: HttpException){
            return Resources.Error(e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException){
            return Resources.Error("Couldn't reach server")
        } catch (e: Exception){
            return Resources.Error("unknown")
        }
    }

    override suspend fun getToken(): Resources<List<TokenEntity>> {
        try{
            val response = movieRepository.getToken()
            return if (response.isSuccessful){
                val responseCode = response.code()
                Log.d("TokenResponse", "Token Receive: $responseCode")
                if (response.body() != null){
                    val result = response.body()!!.map { it.toTokenEntity() }
                    Resources.Success(result)
                } else {
                    Log.d("TokenResponse", "Response Empty")
                    Resources.Empty("Empty Token")
                }
            } else{
                val responseCode = response.code()
                Log.d("TokenResponse", responseCode.toString())
                throw Exception()
            }
        } catch (e: HttpException){
            return Resources.Error(e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException){
            return Resources.Error("Couldn't reach server")
        }

    }
}
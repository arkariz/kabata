package com.arrkariz.kabata.domain.usecase

import com.arrkariz.kabata.data.network.response.toMovieListEntity
import com.arrkariz.kabata.domain.model.MovieListEntity
import com.arrkariz.kabata.domain.model.TokenEntity
import com.arrkariz.kabata.domain.repository.IMovieRepository
import com.arrkariz.kabata.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override suspend fun postToken(token: TokenEntity) = movieRepository.postToken(token)

    override fun getMovieList(): Flow<Resources<List<MovieListEntity>>> = flow {
        try {
            emit(Resources.Loading())
            val movies = movieRepository.getMovieList().map { it.toMovieListEntity() }
            emit(Resources.Success(movies))
        } catch (e: HttpException){
            emit(Resources.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException){
            emit(Resources.Error("Couldn't reach server"))
        }
    }
}
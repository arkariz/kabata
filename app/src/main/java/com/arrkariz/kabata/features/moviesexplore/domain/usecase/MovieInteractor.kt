package com.arrkariz.kabata.features.moviesexplore.domain.usecase

import com.arrkariz.kabata.features.moviesexplore.data.network.response.toMovieEntity
import com.arrkariz.kabata.features.moviesexplore.domain.model.MovieEntity
import com.arrkariz.kabata.features.moviesexplore.domain.repository.IMovieRepository
import com.arrkariz.kabata.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {

    override fun getMovieList(): Flow<Resources<List<MovieEntity>>> = flow {
        try {
            emit(Resources.Loading(message = "loading"))
            val movies = movieRepository.getMovieList()
            if (movies.isSuccessful){
                if (movies.code() == 204){
                    emit(Resources.Empty("There is no new movie yet"))
                } else {
                    emit(Resources.Success(movies.body()!!.map { it.toMovieEntity() }))
                }
            } else {
                throw Exception()
            }
        } catch (e: HttpException){
            emit(Resources.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException){
            emit(Resources.Error("Couldn't reach server"))
        }
    }

    override fun getNewestMovie(): Flow<Resources<MovieEntity>> = flow {
        try {
            emit(Resources.Loading(message = "loading"))
            val movie = movieRepository.getNewestMovie()
            if (movie.isSuccessful){
                if (movie.code() == 204){
                    emit(Resources.Empty("There is no new movie yet"))
                } else {
                    emit(Resources.Success(movie.body()!!.toMovieEntity()))
                }
            } else{
                throw IOException()
            }
        } catch (e: HttpException){
            emit(Resources.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException){
            emit(Resources.Error("Couldn't reach server"))
        } catch (e: Exception) {
            emit(Resources.Error("An unexpected error occurred"))
        }
    }
}
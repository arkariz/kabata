package com.arrkariz.kabata.features.moviesexplore.domain.usecase

import com.arrkariz.kabata.features.moviesexplore.domain.model.MovieEntity
import com.arrkariz.kabata.utils.GetMovieCase
import com.arrkariz.kabata.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMovieUseCase (
    private val getMoviesTest: String,
    private val movie: MovieEntity?
): MovieUseCase {
    override fun getMovieList(): Flow<Resources<List<MovieEntity>>> = flow {
        val movies: MutableList<MovieEntity> = mutableListOf()

        when (getMoviesTest) {
            GetMovieCase.SUCCESS -> {
                movies.add(movie!!)
                emit(Resources.Success(movies))
            }
            GetMovieCase.EMPTY -> {
                emit(Resources.Empty(GetMovieCase.EMPTY))
            }
            else -> {
                emit(Resources.Error(GetMovieCase.ERROR))
            }
        }
    }

    override fun getNewestMovie(): Flow<Resources<MovieEntity>> = flow {

        when (getMoviesTest) {
            GetMovieCase.SUCCESS -> {
                emit(Resources.Success(movie!!))
            }
            GetMovieCase.EMPTY -> {
                emit(Resources.Empty(GetMovieCase.EMPTY))
            }
            else -> {
                emit(Resources.Error(GetMovieCase.ERROR))
            }
        }
    }
}
package com.arrkariz.kabata.features.moviesexplore.domain.usecase

import com.arrkariz.kabata.features.moviesexplore.domain.model.MovieEntity
import com.arrkariz.kabata.features.moviesexplore.domain.model.TokenEntity
import com.arrkariz.kabata.utils.Resources
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getMovieList(): Flow<Resources<List<MovieEntity>>>

    fun getNewestMovie(): Flow<Resources<MovieEntity>>
}
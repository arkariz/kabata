package com.arrkariz.kabata.domain.usecase

import com.arrkariz.kabata.domain.model.MovieListEntity
import com.arrkariz.kabata.domain.model.TokenEntity
import com.arrkariz.kabata.utils.Resources
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    suspend fun postToken(token: TokenEntity)
    suspend fun getMovieList(): Flow<Resources<List<MovieListEntity>>>
}
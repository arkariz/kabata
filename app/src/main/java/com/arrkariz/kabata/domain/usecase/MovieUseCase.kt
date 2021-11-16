package com.arrkariz.kabata.domain.usecase

import com.arrkariz.kabata.domain.model.MovieEntity
import com.arrkariz.kabata.domain.model.TokenEntity
import com.arrkariz.kabata.utils.Resources
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    suspend fun postToken(token: TokenEntity)

    fun getMovieList(): Flow<Resources<List<MovieEntity>>>

    fun getNewestMovie(): Flow<Resources<MovieEntity>>
}
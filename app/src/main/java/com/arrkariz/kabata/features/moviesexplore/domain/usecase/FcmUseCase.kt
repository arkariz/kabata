package com.arrkariz.kabata.features.moviesexplore.domain.usecase

import com.arrkariz.kabata.features.moviesexplore.domain.model.TokenEntity

interface FcmUseCase {
    suspend fun postToken(token: TokenEntity)

    suspend fun getToken(): List<TokenEntity>
}
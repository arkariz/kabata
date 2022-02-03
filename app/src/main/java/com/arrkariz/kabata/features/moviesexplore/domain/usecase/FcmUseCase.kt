package com.arrkariz.kabata.features.moviesexplore.domain.usecase

import com.arrkariz.kabata.features.moviesexplore.domain.model.TokenEntity
import com.arrkariz.kabata.utils.Resources

interface FcmUseCase {
    suspend fun postToken(token: TokenEntity)

    suspend fun getToken(): Resources<List<TokenEntity>>
}
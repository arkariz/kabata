package com.arrkariz.kabata.features.moviesexplore.domain.usecase

import com.arrkariz.kabata.features.moviesexplore.domain.model.TokenEntity
import com.arrkariz.kabata.utils.GetTokenCase
import com.arrkariz.kabata.utils.Resources

class FakeFcmUseCase (
    private  val getTokenCase: String,
    private val tokenTest: TokenEntity?
): FcmUseCase {
    override suspend fun postToken(token: TokenEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getToken(): Resources<List<TokenEntity>> {
        when (getTokenCase) {
            GetTokenCase.SUCCESS -> {
                val token : List<TokenEntity> = listOf(tokenTest!!)
                return Resources.Success(token)
            }
            GetTokenCase.EMPTY -> {
                return Resources.Empty(GetTokenCase.EMPTY)
            }
            else -> {
                return Resources.Error(GetTokenCase.ERROR)
            }
        }
    }
}
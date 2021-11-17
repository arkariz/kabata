package com.arrkariz.kabata.data.network.response

import com.arrkariz.kabata.domain.model.TokenEntity
import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @field:SerializedName("token")
    val token: String
)

fun TokenResponse.toTokenEntity(): TokenEntity{
    return TokenEntity(
        token
    )
}

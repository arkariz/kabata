package com.arrkariz.kabata.data.network.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @field:SerializedName("token")
    val token: String
)

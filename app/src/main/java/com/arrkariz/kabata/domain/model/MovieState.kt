package com.arrkariz.kabata.domain.model

data class MovieState(
    val isLoading: Boolean = false,
    val movie: MovieEntity = MovieEntity(0, "", "", "", "0.0"),
    val error: String = ""
)

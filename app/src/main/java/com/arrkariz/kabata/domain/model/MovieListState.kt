package com.arrkariz.kabata.domain.model

data class MovieListState(
    val isLoading: Boolean = false,
    val movies: List<MovieEntity> = emptyList(),
    val error: String = ""
)

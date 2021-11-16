package com.arrkariz.kabata.domain.model

data class MovieListState(
    val isLoading: Boolean = false,
    val movies: List<MovieListEntity> = emptyList(),
    val error: String = ""
)

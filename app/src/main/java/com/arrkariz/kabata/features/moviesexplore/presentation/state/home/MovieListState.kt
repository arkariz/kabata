package com.arrkariz.kabata.features.moviesexplore.presentation.state.home

import com.arrkariz.kabata.features.moviesexplore.domain.model.MovieEntity

data class MovieListState(
    val isLoading: Boolean = false,
    val movies: List<MovieEntity> = emptyList(),
    val error: String = "",
    val empty: String = ""
)

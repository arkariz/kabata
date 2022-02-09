package com.arrkariz.kabata.features.moviesexplore.presentation.state.home

import com.arrkariz.kabata.features.moviesexplore.domain.model.MovieEntity

data class MovieState(
    val isLoading: Boolean = false,
    val movie: MovieEntity = MovieEntity(0, "", "", "", "0.0", "0.0", "", "", "", ""),
    val error: String = "",
    val empty: String = "",
)

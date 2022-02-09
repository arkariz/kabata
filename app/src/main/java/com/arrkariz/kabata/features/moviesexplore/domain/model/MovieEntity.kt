package com.arrkariz.kabata.features.moviesexplore.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    val id: Int,
    val title: String,
    val year: String,
    val url: String,
    val image: String,
    val star: String,
    val duration: String,
    val genre: String,
    val imdb: String,
    val videoUrl: String
) :Parcelable

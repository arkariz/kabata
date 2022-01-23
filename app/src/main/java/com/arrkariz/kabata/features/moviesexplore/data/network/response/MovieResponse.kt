package com.arrkariz.kabata.features.moviesexplore.data.network.response

import com.arrkariz.kabata.features.moviesexplore.domain.model.MovieEntity
import com.google.gson.annotations.SerializedName

data class MovieResponse(
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("star")
	val star: String,
)

fun MovieResponse.toMovieEntity(): MovieEntity {
	return MovieEntity(
		id = id,
		title = title,
		url = url,
		image = image,
		star = star,
	)
}

package com.arrkariz.kabata.data.network.response

import com.arrkariz.kabata.domain.model.MovieEntity
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
)

fun MovieResponse.toMovieEntity(): MovieEntity {
	return MovieEntity(
		id = id,
		title = title,
		url = url,
		image = image
	)
}

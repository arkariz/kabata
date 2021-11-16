package com.arrkariz.kabata.data.network.response

import com.arrkariz.kabata.domain.model.MovieListEntity
import com.google.gson.annotations.SerializedName

data class MovieListResponse(
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("image")
	val image: String,
)

fun MovieListResponse.toMovieListEntity(): MovieListEntity {
	return MovieListEntity(
		id = id,
		title = title,
		url = url,
		image = image
	)
}

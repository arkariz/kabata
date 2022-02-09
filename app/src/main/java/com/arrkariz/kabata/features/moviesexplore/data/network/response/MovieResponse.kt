package com.arrkariz.kabata.features.moviesexplore.data.network.response

import com.arrkariz.kabata.features.moviesexplore.domain.model.MovieEntity
import com.google.gson.annotations.SerializedName

data class MovieResponse(
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("year")
	val year: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("star")
	val star: String,

	@field:SerializedName("duration")
	val duration: String,

	@field:SerializedName("genre")
	val genre: String,

	@field:SerializedName("imdb")
	val imdb: String,

	@field:SerializedName("video_url")
	val video_url: String,
)

fun MovieResponse.toMovieEntity(): MovieEntity {
	return MovieEntity(
		id = id,
		title = title,
		year = year,
		url = url,
		image = image,
		star = star,
		duration = duration,
		genre = genre,
		imdb = imdb,
		videoUrl = video_url
	)
}

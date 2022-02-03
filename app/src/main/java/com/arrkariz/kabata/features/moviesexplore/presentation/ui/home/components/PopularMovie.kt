package com.arrkariz.kabata.features.moviesexplore.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.arrkariz.kabata.features.moviesexplore.domain.model.MovieEntity
import com.arrkariz.kabata.features.moviesexplore.presentation.state.home.HomeViewModel
import com.arrkariz.kabata.theme.Typography

@ExperimentalCoilApi
@Composable
fun popularMovie(viewModel: HomeViewModel) {
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            "Popular Movies",
            style = Typography.h1,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 16.dp, start = 24.dp)
        )

//        StateContent(state)

        LazyRow {
            items(state.movies.reversed().take(10)) { movies ->
                movieItem(
                    movie = movies,
                    onItemClick = {

                    }
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
private fun movieItem(
    movie: MovieEntity,
    onItemClick: (MovieEntity) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .size(height = 280.dp, width = 140.dp)
            .clickable { onItemClick(movie) }
    ) {
        Box(
            Modifier.size(height = 230.dp, width = 140.dp)
        ) {
            val painter = rememberImagePainter(movie.image)
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
                    .clip(RoundedCornerShape(7.dp))
            )
            if (painter.state is ImagePainter.State.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

        ratingStar(movie)

        Box(
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .align(Alignment.Start)
        ) {
            Text(
                movie.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = Typography.h3,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}
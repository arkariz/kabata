package com.arrkariz.kabata.features.moviesexplore.presentation.ui.home.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.arrkariz.kabata.features.detailmovie.presentation.DetailMovieActivity
import com.arrkariz.kabata.features.moviesexplore.presentation.state.home.HomeViewModel
import com.arrkariz.kabata.features.moviesexplore.presentation.ui.home.StateContent
import com.arrkariz.kabata.features.moviesexplore.presentation.ui.home.ratingStar
import com.arrkariz.kabata.theme.Typography

@Composable
fun NewMovie(viewModel: HomeViewModel) {
    val state = viewModel.newMovieState.value
    val context = LocalContext.current
    Column(
        Modifier.padding(start = 24.dp, end = 24.dp, bottom = 15.dp, top = 10.dp)
            .fillMaxWidth()
            .clickable {
                val intent = Intent(context, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, state.movie)
                context.startActivity(intent)
            }
    ) {
        Text(
            "New Release",
            modifier = Modifier.align(Alignment.Start)
                .padding(bottom = 16.dp),
            style = Typography.h1
        )

        StateContent(state)

        Box(
            Modifier.height(155.dp)
        ) {
            Image(
                painter = rememberImagePainter(state.movie.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
                    .clip(RoundedCornerShape(7.dp))
            )
        }

        ratingStar(state.movie.star)

        Text(
            state.movie.title,
            style = Typography.h2,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.align(Alignment.Start).padding(top = 3.dp)
        )
    }
}
package com.arrkariz.kabata.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.arrkariz.kabata.presentation.HomeViewModel
import com.arrkariz.kabata.presentation.compose.StateContent
import com.arrkariz.kabata.presentation.ui.theme.Typography

@Composable
fun newMovie(viewModel: HomeViewModel) {
    val state = viewModel.newMovieState.value
    Column(
        Modifier.padding(24.dp)
            .fillMaxWidth()
    ) {
        Text(
            "New Release",
            modifier = Modifier.align(Alignment.Start)
                .padding(bottom = 16.dp),
            style = Typography.h1
        )

        StateContent(state)

        Box(
            Modifier.height(190.dp)
        ) {
            Image(
                painter = rememberImagePainter(state.movie.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
                    .clip(RoundedCornerShape(7.dp))
            )
        }

        ratingStar(state.movie)

        Text(
            state.movie.title,
            style = Typography.h2,
            modifier = Modifier.align(Alignment.Start).padding(top = 3.dp)
        )
    }
}
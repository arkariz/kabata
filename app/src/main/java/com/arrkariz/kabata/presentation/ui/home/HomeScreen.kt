package com.arrkariz.kabata.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.arrkariz.kabata.R
import com.arrkariz.kabata.domain.model.MovieEntity
import com.arrkariz.kabata.domain.model.MovieState
import com.arrkariz.kabata.presentation.HomeViewModel
import com.arrkariz.kabata.presentation.ui.home.headerApp
import com.arrkariz.kabata.presentation.ui.home.newMovie
import com.arrkariz.kabata.presentation.ui.home.popularMovie
import com.arrkariz.kabata.presentation.ui.home.ratingStar
import com.arrkariz.kabata.presentation.ui.theme.Typography
import org.koin.androidx.compose.getViewModel

@ExperimentalCoilApi
@Composable
fun HomeScreen() {
    val viewModel = getViewModel<HomeViewModel>()
    Surface(color = MaterialTheme.colors.background) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                headerApp("Alex")
                newMovie(viewModel)
                popularMovie(viewModel)
            }
        }
    }
}



@Composable
fun StateContent(state: MovieState) {
    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    if (state.error.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                state.error
            )
        }
    }
}
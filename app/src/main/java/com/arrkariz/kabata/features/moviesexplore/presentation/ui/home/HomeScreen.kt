package com.arrkariz.kabata.features.moviesexplore.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.annotation.ExperimentalCoilApi
import com.arrkariz.kabata.R
import com.arrkariz.kabata.features.moviesexplore.presentation.state.home.MovieState
import com.arrkariz.kabata.features.moviesexplore.presentation.state.home.HomeViewModel
import com.arrkariz.kabata.features.moviesexplore.presentation.ui.home.headerApp
import com.arrkariz.kabata.features.moviesexplore.presentation.ui.home.newMovie
import com.arrkariz.kabata.features.moviesexplore.presentation.ui.home.popularMovie
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
    if (state.empty.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                state.empty
            )
        }
    }
}
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
import com.arrkariz.kabata.presentation.HomeViewModel
import com.arrkariz.kabata.presentation.ui.theme.Typography
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(){
    val viewModel = getViewModel<HomeViewModel>()
    Surface(color = MaterialTheme.colors.background) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Image(
                painter = painterResource(R.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeaderApp("Alex")
                NewMovie(viewModel)
                PopularMovie(viewModel)
            }
        }
    }
}

@Composable
fun HeaderApp(name: String){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(top = 18.dp, start = 24.dp, end = 24.dp)
            .fillMaxWidth()
    ){
        Column {
            Text("Howdy $name!", style = Typography.h1)
            Text("Explore the World Through Movie", style = Typography.body1)
        }

        Image(
            painter = painterResource(R.drawable.user),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(60.dp)),
        )
    }
}

@Composable
fun NewMovie(viewModel: HomeViewModel){
    val state = viewModel.newMovieState.value
    Column(
        Modifier.padding(24.dp)
            .fillMaxWidth()
    ){
        Text("New Release",
            modifier = Modifier.align(Alignment.Start)
                                .padding(bottom = 16.dp),
            style = Typography.h1
        )

        Box(
            Modifier.height(190.dp)
        ){
            Image(
                painter = rememberImagePainter(state.movie.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
                                    .clip(RoundedCornerShape(7.dp))
            )
        }

        Text(state.movie.title,
            style = Typography.h2,
            modifier = Modifier.align(Alignment.Start).padding(top = 10.dp)
        )
    }
}

@ExperimentalCoilApi
@Composable
fun PopularMovie(viewModel: HomeViewModel){
    val state = viewModel.state.value
    Column {
        Text(
            "Popular Movies",
            style = Typography.h1,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 16.dp, start = 24.dp)
        )
        LazyRow{
            items(state.movies.reversed()){ movies ->
                MovieItem(
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
private fun MovieItem(
    movie: MovieEntity,
    onItemClick: (MovieEntity) -> Unit
    ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .size(height = 280.dp, width = 140.dp)
            .clickable{ onItemClick(movie) }
    ){
        Box(
            Modifier.size(height = 230.dp, width = 140.dp)
        ){
            val painter = rememberImagePainter(movie.image)
            if (painter.state is ImagePainter.State.Loading){
                CircularProgressIndicator()
            } else{
                Image(
                    painter = rememberImagePainter(movie.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                        .clip(RoundedCornerShape(7.dp))
                )
            }

        }

        Text(
            movie.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = Typography.h2,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
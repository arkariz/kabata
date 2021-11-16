package com.arrkariz.kabata.presentation.compose

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.arrkariz.kabata.R
import com.arrkariz.kabata.domain.model.MovieListEntity
import com.arrkariz.kabata.domain.model.TokenEntity
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
                NewMovie("Test", "https://picsum.photos/300/300")
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
fun NewMovie(title:String, imgUrl: String){
    Column(
        Modifier.padding(24.dp)
            .fillMaxWidth()
    ){
        Text("New Movie",
            modifier = Modifier.align(Alignment.Start)
                                .padding(bottom = 16.dp),
            style = Typography.h1
        )

        Box(
            Modifier.height(200.dp)
        ){
            Image(
                painter = rememberImagePainter(imgUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
                                    .clip(RoundedCornerShape(7.dp))
            )
        }

        Text(title, Modifier.align(Alignment.Start).padding(top = 10.dp), fontSize = 16.sp)
    }
}

@Composable
fun PopularMovie(viewModel: HomeViewModel){
    val state = viewModel.state.value
    LazyRow(modifier = Modifier.wrapContentSize(unbounded = true)){
        items(state.movies){ movies ->
            MovieItem(
                movie = movies,
                onItemClick = {

                }
            )

        }
    }
}

@Composable
private fun MovieItem(
    movie: MovieListEntity,
    onItemClick: (MovieListEntity) -> Unit
    ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable{ onItemClick(movie) }
    ){
        Box(
            Modifier.size(height = 230.dp, width = 140.dp)
        ){
            Image(
                painter = rememberImagePainter(movie.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
                    .clip(RoundedCornerShape(7.dp))
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 9.dp)
        ){
            Card(
                backgroundColor = Color(0xFF242424),
                modifier = Modifier.clip(RoundedCornerShape(3.dp))
            ){
                Text("21", Modifier.padding(2.dp))
            }
            Card(
                backgroundColor = Color(0xFF242424),
                modifier = Modifier.clip(RoundedCornerShape(3.dp))
            ){
                Text("horror", Modifier.padding(2.dp))
            }
            Card(
                backgroundColor = Color(0xFF242424),
                modifier = Modifier.clip(RoundedCornerShape(3.dp))
            ){
                Text("rating", Modifier.padding(2.dp))
            }
        }
        Text(movie.title, fontSize = 26.sp)
    }
}
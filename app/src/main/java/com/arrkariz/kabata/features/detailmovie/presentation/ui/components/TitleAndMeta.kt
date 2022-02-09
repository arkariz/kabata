package com.arrkariz.kabata.features.detailmovie.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.arrkariz.kabata.features.moviesexplore.domain.model.MovieEntity
import com.arrkariz.kabata.theme.Typography
import com.arrkariz.kabata.theme.YellowRating

@Composable
fun TitleAndMeta(movie: MovieEntity){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            Modifier.width(300.dp),
            contentAlignment = Alignment.Center
        ){
            Text(
                movie.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = Typography.h1.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
        }
        Spacer(Modifier.height(5.dp))
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            movieAttribute(movie.duration)
            movieAttribute(movie.genre)
            movieAttribute(movie.star, true)
        }
    }
}

@Composable
fun movieAttribute(text: String, isRating: Boolean = false) {
    Button(
        onClick = {},
        enabled = false,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(disabledBackgroundColor = Color.DarkGray.copy(alpha = 0.4f)),
        modifier = Modifier.wrapContentHeight().wrapContentWidth().padding(end = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isRating) Icon(Icons.Filled.Star, "ratingStar", tint = YellowRating)
            Text(
                text,
                color = Color.White,
                style = Typography.body1
            )
        }
    }
}
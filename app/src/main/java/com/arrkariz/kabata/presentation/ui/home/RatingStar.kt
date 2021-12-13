package com.arrkariz.kabata.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arrkariz.kabata.R
import com.arrkariz.kabata.domain.model.MovieEntity
import com.arrkariz.kabata.presentation.ui.theme.Typography
import com.arrkariz.kabata.presentation.ui.theme.YellowRating

@Composable
fun ratingStar(
    movie: MovieEntity
) {
    Row(
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            movie.star,
            style = Typography.h3,
            color = YellowRating,
            modifier = Modifier
                .padding(end = 3.dp)
        )
        when (movie.star.toFloat()) {
            in 1.0f..2.9f -> {
                Image(
                    painter = painterResource(R.drawable.ic_star_yellow),
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp)
                )
                for (i in 1..4) {
                    Image(
                        painter = painterResource(R.drawable.ic_star_white),
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.dp)
                    )
                }
            }
            in 3.0f..4.9f -> {
                for (i in 1..2) {
                    Image(
                        painter = painterResource(R.drawable.ic_star_yellow),
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.dp)
                    )
                }
                for (i in 1..3) {
                    Image(
                        painter = painterResource(R.drawable.ic_star_white),
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.dp)
                    )
                }
            }
            in 5.0f..6.9f -> {
                for (i in 1..3) {
                    Image(
                        painter = painterResource(R.drawable.ic_star_yellow),
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.dp)
                    )
                }
                for (i in 1..2) {
                    Image(
                        painter = painterResource(R.drawable.ic_star_white),
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.dp)
                    )
                }
            }
            in 7.0f..8.9f -> {
                for (i in 1..4) {
                    Image(
                        painter = painterResource(R.drawable.ic_star_yellow),
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.dp)
                    )
                }
                Image(
                    painter = painterResource(R.drawable.ic_star_white),
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp)
                )
            }
            in 9.0f..10.0f -> {
                for (i in 1..5) {
                    Image(
                        painter = painterResource(R.drawable.ic_star_yellow),
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.dp)
                    )
                }
            }
        }
    }
}
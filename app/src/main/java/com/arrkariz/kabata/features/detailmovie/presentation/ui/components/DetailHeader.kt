package com.arrkariz.kabata.features.detailmovie.presentation.ui.components

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.arrkariz.kabata.R
import com.arrkariz.kabata.features.detailmovie.presentation.DetailMovieActivity

@Composable
fun DetailHeader(configuration: Configuration, image: String) {
    val activity = (LocalContext.current as? Activity)

    Box(
        Modifier.height(((configuration.screenHeightDp * 0.75).dp))
    ) {
        val painter = rememberImagePainter(image)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height((configuration.screenHeightDp * 0.75).dp),
            contentScale = ContentScale.Crop,
        )
        Spacer(
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Black,
                            Color.Transparent,
                        )
                    )
                )
                .align(Alignment.TopCenter)
        )

        Spacer(
            Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        )
                    )
                )
                .align(Alignment.BottomCenter)
        )

        Surface(
            color = Color.DarkGray.copy(alpha = 0.78f),
            shape = RoundedCornerShape(30),
            modifier = Modifier
                .padding(start = 30.dp, top = 50.dp)
                .clickable { activity?.finish() }
        ) {
            Icon(
                Icons.Default.KeyboardArrowLeft,
                "arrow back",
                tint = Color.White,
                modifier = Modifier.size(36.dp).padding(4.dp)
            )
        }
    }
}
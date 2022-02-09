package com.arrkariz.kabata.features.detailmovie.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.arrkariz.kabata.features.detailmovie.presentation.ui.detailMovieScreen
import com.arrkariz.kabata.features.moviesexplore.domain.model.MovieEntity
import com.arrkariz.kabata.theme.KabataTheme

class DetailMovieActivity : ComponentActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            KabataTheme {
                val movie = intent.getParcelableExtra<MovieEntity>(EXTRA_MOVIE) as MovieEntity
                detailMovieScreen(movie)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    KabataTheme {
//        detailMovieScreen()
    }
}
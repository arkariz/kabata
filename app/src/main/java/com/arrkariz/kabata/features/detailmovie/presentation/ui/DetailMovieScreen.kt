package com.arrkariz.kabata.features.detailmovie.presentation.ui

import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.startActivity
import com.arrkariz.kabata.R
import com.arrkariz.kabata.features.detailmovie.presentation.ui.components.DetailHeader
import com.arrkariz.kabata.features.detailmovie.presentation.ui.components.TitleAndMeta
import com.arrkariz.kabata.features.moviesexplore.domain.model.MovieEntity
import com.arrkariz.kabata.theme.RedButton
import com.arrkariz.kabata.theme.Typography
import com.jwplayer.pub.api.configuration.PlayerConfig
import com.jwplayer.pub.api.license.LicenseUtil
import com.jwplayer.pub.api.media.playlists.PlaylistItem
import com.jwplayer.pub.view.JWPlayerView
import kotlin.math.roundToInt


@Composable
fun detailMovieScreen(movie: MovieEntity) {
    val configuration = LocalConfiguration.current
    val context = LocalContext.current
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetailHeader(configuration, movie.image)
            Box() {
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black,
                                    Color.Transparent,
                                )
                            )
                        )

                )
                TitleAndMeta(movie)
            }
            Button(
                onClick = {
                    val webIntent: Intent = Uri.parse(movie.videoUrl).let { webpage ->
                        Intent(Intent.ACTION_VIEW, webpage)
                    }
                    startActivity(context, webIntent, null)},
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(backgroundColor = RedButton),
                modifier = Modifier.fillMaxWidth(0.85f)
            ) {
                Text(
                    "WATCH NOW",
                    color = Color.White,
                    style = Typography.h2.copy(fontWeight = FontWeight.Bold)
                )

            }
        }
    }
}
//
//@Composable
//fun Player(license: String, url: String) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        AndroidView(
//            factory = { context ->
//                LicenseUtil.setLicenseKey(context, license)
//
//                val playlistItem = PlaylistItem.Builder()
//                    .file(url)
//                    .build()
//
//                val playlist: MutableList<PlaylistItem> = ArrayList()
//                playlist.add(playlistItem)
//
//                val config = PlayerConfig.Builder()
//                    .playlist(playlist)
//                    .build()
//                JWPlayerView(context).apply {
//                    player.setup(config)
//                }
//            }
//        )
//    }
//}
//
//@Composable
//fun urlIntent(url:String){
//    val context = LocalContext.current
//    val webIntent: Intent = Uri.parse(url).let { webpage ->
//        Intent(Intent.ACTION_VIEW, webpage)
//    }
//    startActivity(context, webIntent, null)
//}
//
//@Composable
//fun loadWebUrl(url: String) {
//    val configuration = LocalConfiguration.current
//    val screenHeight = configuration.screenHeightDp * 0.4
//    AndroidView(factory = {
//        WebView(it).apply {
//            webViewClient = WebViewClient()
//            settings.javaScriptEnabled = true
//            layoutParams.width = configuration.screenWidthDp
//            layoutParams.height = screenHeight.roundToInt()
//            loadUrl(url)
////            loadData("<iframe src=\"//databasegdriveplayer.co///databasegdriveplayer.co/player.php?imdb=tt15726046\" frameborder=\"0\" width=\"100%\" height=\"400\" allowfullscreen=\"allowfullscreen\"> </iframe>", "text/html", "utf-8")
//        }
//    })
//}
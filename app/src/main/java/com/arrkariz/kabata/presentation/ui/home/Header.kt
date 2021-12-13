package com.arrkariz.kabata.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arrkariz.kabata.R
import com.arrkariz.kabata.presentation.ui.theme.Typography

@Composable
fun headerApp(name: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(top = 18.dp, start = 24.dp, end = 24.dp)
            .fillMaxWidth()
    ) {
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
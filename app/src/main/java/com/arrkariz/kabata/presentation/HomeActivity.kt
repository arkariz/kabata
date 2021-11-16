package com.arrkariz.kabata.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arrkariz.kabata.presentation.compose.HomeScreen
import com.arrkariz.kabata.presentation.ui.theme.KabataTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KabataTheme {
                // A surface container using the 'background' color from the theme
                HomeScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KabataTheme {
        HomeScreen()
    }
}
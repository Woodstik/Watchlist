package com.example.watchlist.ui.auth.welcome

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.watchlist.ui.theme.WatchlistTheme

@Composable
fun WelcomeScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Text(text = "Auth Menu Screen")
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    WatchlistTheme {
        WelcomeScreen()
    }
}

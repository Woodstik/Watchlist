package com.example.watchlist.ui.auth.signUp

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.watchlist.ui.theme.WatchlistTheme

@Composable
fun SignUpScreen() {
    Text("SignUp!")
}

@Preview()
@Composable
private fun SignUpScreenDarkPreview() {
    WatchlistTheme(darkTheme = true) { SignUpScreen() }
}

@Preview()
@Composable
private fun SignUpScreenLightPreview() {
    WatchlistTheme { SignUpScreen() }
}

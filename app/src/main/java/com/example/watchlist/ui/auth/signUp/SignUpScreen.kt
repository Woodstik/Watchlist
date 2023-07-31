package com.example.watchlist.ui.auth.signUp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.watchlist.ui.theme.WatchlistTheme

@Composable
fun SignUpScreen(email: String = "") {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Text("SignUp with $email!")
    }
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

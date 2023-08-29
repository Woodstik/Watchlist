@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.watchlist.ui.auth.login

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.watchlist.ui.composable.Toolbar
import com.example.watchlist.ui.theme.WatchlistTheme

@Composable
fun LoginScreen(
    onPasswordChange: (String) -> Unit = {},
    onClickForgotPassword :() -> Unit = {},
    onClickSubmit: () -> Unit = {},
    onClickBack: () -> Unit = {},
){
    Scaffold(
        topBar = { Toolbar(onClickBack = onClickBack) },
    ) { paddingValues ->
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenDarkPreview() {
    WatchlistTheme(darkTheme = true) {
        LoginScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenLightPreview() {
    WatchlistTheme {
        LoginScreen()
    }
}
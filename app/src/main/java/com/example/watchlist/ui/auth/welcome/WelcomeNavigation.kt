package com.example.watchlist.ui.auth.welcome

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_WELCOME = "welcome"
fun NavGraphBuilder.welcomeScreen() {
    composable(ROUTE_WELCOME) {
        val viewModel = hiltViewModel<WelcomeViewModel>()
        val state = viewModel.state
        WelcomeScreen()
    }
}

fun NavController.navigateToWelcome() {
    navigate(ROUTE_WELCOME)
}

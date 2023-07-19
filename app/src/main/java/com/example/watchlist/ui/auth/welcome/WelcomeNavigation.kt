package com.example.watchlist.ui.auth.welcome

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_WELCOME = "welcome"
fun NavGraphBuilder.welcomeScreen() {
    composable(ROUTE_WELCOME) { WelcomeScreen() }
}

fun NavController.navigateToWelcome() {
    navigate(ROUTE_WELCOME)
}

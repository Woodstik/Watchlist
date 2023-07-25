package com.example.watchlist.ui.auth.welcome

import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_WELCOME = "welcome"
fun NavGraphBuilder.welcomeScreen() {
    composable(ROUTE_WELCOME) {
        val viewModel = hiltViewModel<WelcomeViewModel>()
        val state by viewModel.state.observeAsState(WelcomeScreenState())
        WelcomeScreen(
            state = state,
        )
    }
}

fun NavController.navigateToWelcome() {
    navigate(ROUTE_WELCOME)
}

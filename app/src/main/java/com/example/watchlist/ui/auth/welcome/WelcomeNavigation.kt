package com.example.watchlist.ui.auth.welcome

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_WELCOME = "welcome"
fun NavGraphBuilder.welcomeScreen() {
    composable(ROUTE_WELCOME) {
        val context = LocalContext.current
        val viewModel = hiltViewModel<WelcomeViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle(WelcomeScreenState())
        WelcomeScreen(
            state = state,
            onEmailChange = { viewModel.onEmailChange(it) },
            onClickContinueEmail = { viewModel.onClickContinueEmail() },
            onClickContinueGoogle = { Toast.makeText(context, "Continue to Google!", Toast.LENGTH_SHORT).show() },
            onClickContinueFacebook = { Toast.makeText(context, "Continue to Facebook!", Toast.LENGTH_SHORT).show() },
        )
    }
}

fun NavController.navigateToWelcome() {
    navigate(ROUTE_WELCOME)
}

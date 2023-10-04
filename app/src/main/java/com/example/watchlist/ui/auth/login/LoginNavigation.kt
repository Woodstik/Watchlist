package com.example.watchlist.ui.auth.login

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val ROUTE_LOGIN = "login"
private const val ARG_EMAIL = "email"
private const val ARG_NAME = "name"

internal class LoginArgs(val email: String, val name: String) {
    constructor(savedStateHandle: SavedStateHandle) :
        this(
            checkNotNull(savedStateHandle[ARG_EMAIL]) as String,
            checkNotNull(savedStateHandle[ARG_NAME]) as String,
        )
}

fun NavGraphBuilder.loginScreen(
    onGoBack: () -> Unit,
    onGoToHome: () -> Unit,
    onGoToForgotPassword: (email: String) -> Unit,
) {
    composable(
        route = "$ROUTE_LOGIN/{$ARG_EMAIL}/{$ARG_NAME}",
        arguments = listOf(
            navArgument(ARG_EMAIL) { type = NavType.StringType },
            navArgument(ARG_NAME) { type = NavType.StringType },
        ),
    ) {
        val viewModel = hiltViewModel<LoginViewModel>()
        val screenState by viewModel.screenState.collectAsStateWithLifecycle()
        val navState by viewModel.navState.collectAsStateWithLifecycle()
        LaunchedEffect(navState) {
            navState?.let {
                when (it) {
                    is LoginDestination.ForgotPassword -> onGoToForgotPassword(it.email)
                    LoginDestination.Home -> onGoToHome()
                }
                viewModel.onNavigate()
            }
        }
        LoginScreen(
            state = screenState,
            onClickBack = { onGoBack() },
            onClickSubmit = { viewModel.onClickSubmit() },
            onPasswordChange = { viewModel.onPasswordChange(it) },
            onClickForgotPassword = { viewModel.onClickForgotPassword() },
        )
    }
}

fun NavController.navigateToLogin(email: String, name: String) {
    navigate("$ROUTE_LOGIN/$email/$name")
}

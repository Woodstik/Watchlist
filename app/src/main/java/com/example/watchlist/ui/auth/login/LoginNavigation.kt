package com.example.watchlist.ui.auth.login

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val ROUTE_LOGIN = "login"
private const val ARG_EMAIL = "email"
private const val ARG_NAME = "name"

internal class LoginArgs(val email: String, val name: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                checkNotNull(savedStateHandle[ARG_EMAIL]) as String,
                checkNotNull(savedStateHandle[ARG_NAME]) as String
            )
}

fun NavGraphBuilder.loginScreen(
    onGoBack: () -> Unit
) {
    composable(
        route = "$ROUTE_LOGIN/{$ARG_EMAIL}/{$ARG_NAME}",
        arguments = listOf(
            navArgument(ARG_EMAIL) { type = NavType.StringType },
            navArgument(ARG_NAME) { type = NavType.StringType }
        ),
    ) {
        val viewModel = hiltViewModel<LoginViewModel>()
        LoginScreen(
            onClickBack = { onGoBack() },
            onClickSubmit = { viewModel.onClickSubmit() },
            onPasswordChange = { viewModel.onPasswordChange(it) },
            onClickForgotPassword = { viewModel.onClickForgotPassword() }
        )
    }
}

fun NavController.navigateToLogin(email: String, name: String) {
    navigate("$ROUTE_LOGIN/$email/$name")
}

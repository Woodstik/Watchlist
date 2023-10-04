package com.example.watchlist.ui.auth.forgotPassword

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val ROUTE_FORGOT_PASSWORD = "forgot_password"
private const val ARG_EMAIL = "email"
internal class ForgotPasswordArgs(val email: String) {
    constructor(savedStateHandle: SavedStateHandle) :
        this(checkNotNull(savedStateHandle[ARG_EMAIL]) as String)
}

fun NavGraphBuilder.forgotPasswordScreen(
    onGoBack: () -> Unit,
    onGoToVerifyEmail: () -> Unit,
) {
    composable(
        route = "$ROUTE_FORGOT_PASSWORD/{$ARG_EMAIL}",
        arguments = listOf(navArgument(ARG_EMAIL) { type = NavType.StringType }),
    ) {
        val viewModel = hiltViewModel<ForgotPasswordViewModel>()
        val screenState by viewModel.screenState.collectAsStateWithLifecycle()
        ForgotPasswordScreen(
            state = screenState,
        )
    }
}

fun NavController.navigateToForgotPassword(email: String) {
    navigate("$ROUTE_FORGOT_PASSWORD/$email")
}

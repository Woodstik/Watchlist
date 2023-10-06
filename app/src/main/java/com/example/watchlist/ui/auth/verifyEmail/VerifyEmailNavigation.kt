package com.example.watchlist.ui.auth.verifyEmail

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

private const val ROUTE_VERIFY_EMAIL = "verify_email"
private const val ARG_EMAIL = "email"

internal class VerifyEmailArgs(val email: String) {
    constructor(savedStateHandle: SavedStateHandle) :
        this(checkNotNull(savedStateHandle[ARG_EMAIL]) as String)
}

fun NavGraphBuilder.verifyEmailScreen(
    onGoBack: () -> Unit,
    onGoToHome: () -> Unit,
) {
    composable(
        route = "$ROUTE_VERIFY_EMAIL/{$ARG_EMAIL}",
        arguments = listOf(navArgument(ARG_EMAIL) { type = NavType.StringType }),
    ) {
        val viewModel = hiltViewModel<VerifyEmailViewModel>()
        val screenState by viewModel.screenState.collectAsStateWithLifecycle()
        val navState by viewModel.navState.collectAsStateWithLifecycle()
        LaunchedEffect(navState) {
            navState?.let {
                when (it) {
                    is VerifyEmailDestination.Home -> onGoToHome()
                }
                viewModel.onNavigate()
            }
        }
        VerifyEmailScreen(
            state = screenState,
            onClickBack = { onGoBack() },
        )
    }
}

fun NavController.navigateToVerifyEmail(email: String) {
    navigate("$ROUTE_VERIFY_EMAIL/$email")
}

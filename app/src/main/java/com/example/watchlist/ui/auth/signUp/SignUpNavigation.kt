package com.example.watchlist.ui.auth.signUp

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

const val ROUTE_SIGN_UP = "sign_up"
private const val ARG_EMAIL = "email"

internal class SignUpArgs(val email: String) {
    constructor(savedStateHandle: SavedStateHandle) :
        this(checkNotNull(savedStateHandle[ARG_EMAIL]) as String)
}

fun NavGraphBuilder.signUpScreen(
    onGoBack: () -> Unit,
    onGoToVerifyEmail: () -> Unit,
) {
    composable(
        route = "$ROUTE_SIGN_UP/{$ARG_EMAIL}",
        arguments = listOf(navArgument(ARG_EMAIL) { type = NavType.StringType }),
    ) {
        val viewModel = hiltViewModel<SignUpViewModel>()
        val screenState by viewModel.screenState.collectAsStateWithLifecycle()
        val navState by viewModel.navState.collectAsStateWithLifecycle()
        LaunchedEffect(navState) {
            navState?.let {
                when (it) {
                    is SignUpDestinations.VerifyEmail -> onGoToVerifyEmail()
                }
                viewModel.onNavigate()
            }
        }
        SignUpScreen(
            state = screenState,
            onPasswordChange = { viewModel.onPasswordChange(it) },
            onNameChange = { viewModel.onNameChange(it) },
            onClickSubmit = { viewModel.signUp() },
            onClickBack = { onGoBack() },
        )
    }
}

fun NavController.navigateToSignUp(email: String) {
    navigate("$ROUTE_SIGN_UP/$email")
}

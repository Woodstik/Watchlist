package com.example.watchlist.ui.auth.signUp

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val ROUTE_SIGN_UP = "sign_up"
private const val ARG_EMAIL = "email"
fun NavGraphBuilder.signUpScreen() {
    composable(
        route = "$ROUTE_SIGN_UP/{$ARG_EMAIL}",
        arguments = listOf(navArgument(ARG_EMAIL) { type = NavType.StringType }),
    ) {
        SignUpScreen()
    }
}

fun NavController.navigateToSignUp(email: String) {
    navigate("$ROUTE_SIGN_UP/$email")
}

package com.example.watchlist.ui.auth.signUp

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE_SIGN_UP = "sign_up"
fun NavGraphBuilder.signUpScreen() {
    composable(ROUTE_SIGN_UP) {
        SignUpScreen()
    }
}

fun NavController.navigateToSignUp() {
    navigate(ROUTE_SIGN_UP)
}

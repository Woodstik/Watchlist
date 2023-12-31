package com.example.watchlist.ui.auth

import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.example.watchlist.ui.auth.forgotPassword.forgotPasswordScreen
import com.example.watchlist.ui.auth.forgotPassword.navigateToForgotPassword
import com.example.watchlist.ui.auth.login.loginScreen
import com.example.watchlist.ui.auth.login.navigateToLogin
import com.example.watchlist.ui.auth.signUp.navigateToSignUp
import com.example.watchlist.ui.auth.signUp.signUpScreen
import com.example.watchlist.ui.auth.welcome.ROUTE_WELCOME
import com.example.watchlist.ui.auth.welcome.welcomeScreen

const val AUTH_GRAPH_ROUTE_PATTERN = "auth"
fun NavController.navigateToAuthGraph(navOptions: NavOptions? = null) {
    this.navigate(AUTH_GRAPH_ROUTE_PATTERN, navOptions)
}

fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation(
        startDestination = ROUTE_WELCOME,
        route = AUTH_GRAPH_ROUTE_PATTERN,
    ) {
        welcomeScreen(
            onGoToLogin = { email, name -> navController.navigateToLogin(email, name) },
            onGoToSignUp = { navController.navigateToSignUp(it) },
        )
        signUpScreen(
            onGoBack = { navController.popBackStack() },
            onGoToVerifyEmail = { Toast.makeText(navController.context, "Go to Verify Email!", Toast.LENGTH_SHORT).show() },
        )
        loginScreen(
            onGoBack = { navController.popBackStack() },
            onGoToForgotPassword = { navController.navigateToForgotPassword(it) },
            onGoToHome = { Toast.makeText(navController.context, "Go to Home!", Toast.LENGTH_SHORT).show() },
            onGoToVerifyEmail = { Toast.makeText(navController.context, "Go to Verify Email!", Toast.LENGTH_SHORT).show() },
        )
        forgotPasswordScreen(
            onGoBack = { navController.popBackStack() },
        )
    }
}

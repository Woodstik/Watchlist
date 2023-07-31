package com.example.watchlist.ui.auth

import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
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
            onGoToLogin = {
                Toast.makeText(navController.context, "Go to Login!", Toast.LENGTH_SHORT).show()
            },
            onGoToSignUp = { navController.navigateToSignUp(it) },
        )
        signUpScreen()
    }
}

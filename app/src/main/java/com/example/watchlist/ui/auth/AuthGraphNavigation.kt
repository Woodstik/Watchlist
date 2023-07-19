package com.example.watchlist.ui.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
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
        welcomeScreen()
    }
}

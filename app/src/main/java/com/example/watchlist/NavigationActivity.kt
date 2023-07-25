package com.example.watchlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.watchlist.ui.auth.AUTH_GRAPH_ROUTE_PATTERN
import com.example.watchlist.ui.auth.authGraph
import com.example.watchlist.ui.theme.WatchlistTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            WatchlistTheme {
                NavHost(
                    navController = navController,
                    startDestination = AUTH_GRAPH_ROUTE_PATTERN,
                ) {
                    authGraph(navController)
                }
            }
        }
    }
}

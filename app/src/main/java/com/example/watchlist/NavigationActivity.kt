package com.example.watchlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.watchlist.ui.auth.welcome.WelcomeScreen
import com.example.watchlist.ui.theme.WatchlistTheme

class NavigationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            WatchlistTheme {
                NavHost(
                    navController = navController,
                    startDestination = "welcome",
                ) {
                    composable("welcome") { WelcomeScreen() }
                }
            }
        }
    }
}

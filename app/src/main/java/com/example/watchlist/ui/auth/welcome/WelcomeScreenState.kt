package com.example.watchlist.ui.auth.welcome

data class WelcomeScreenState(
    val email: String = "",
    val enabledContinue: Boolean = false,
    val enabledGoogle: Boolean = true,
    val enabledFacebook: Boolean = true,
)

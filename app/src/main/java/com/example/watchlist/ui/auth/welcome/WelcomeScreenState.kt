package com.example.watchlist.ui.auth.welcome

data class WelcomeScreenState(
    val email: String = "",
    val emailError: EmailError = EmailError.NONE,
    val enabledContinue: Boolean = false,
    val enabledGoogle: Boolean = true,
    val enabledFacebook: Boolean = true,
)

enum class EmailError {
    NONE,
    INVALID_EMAIL,
}

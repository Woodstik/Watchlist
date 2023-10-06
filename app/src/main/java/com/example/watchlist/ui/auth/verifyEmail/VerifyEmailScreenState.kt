package com.example.watchlist.ui.auth.verifyEmail

data class VerifyEmailScreenState(
    val email: String = "",
)

sealed interface VerifyEmailDestination {
    object Home : VerifyEmailDestination
}

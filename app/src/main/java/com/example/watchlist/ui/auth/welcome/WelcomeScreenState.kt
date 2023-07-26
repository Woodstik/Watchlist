package com.example.watchlist.ui.auth.welcome

import com.example.watchlist.data.enums.EmailStatus

data class WelcomeScreenState(
    val email: String = "",
    val emailStatus: EmailStatus = EmailStatus.NONE,
    val enabledContinue: Boolean = false,
    val enabledGoogle: Boolean = true,
    val enabledFacebook: Boolean = true,
)

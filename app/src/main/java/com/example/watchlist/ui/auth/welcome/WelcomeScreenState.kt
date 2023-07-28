package com.example.watchlist.ui.auth.welcome

import com.example.watchlist.data.enum.EmailStatus

data class WelcomeScreenState(
    val emailFormState: EmailFormState = EmailFormState(),
    val enabledGoogle: Boolean = true,
    val enabledFacebook: Boolean = true,
)

data class EmailFormState(
    val email: String = "",
    val emailStatus: EmailStatus = EmailStatus.NONE,
    val enabledContinue: Boolean = false,
    val showContinueInProgress: Boolean = false,
)

package com.example.watchlist.ui.auth.login

import com.example.watchlist.data.model.SubmitState
import com.example.watchlist.data.response.LoginResponse

data class LoginScreenState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val submitState: SubmitState<LoginResponse> = SubmitState.Idle,
) {
    val enableSubmit: Boolean
        get() = password.isNotEmpty() && submitState !is SubmitState.InProgress

    val enableForgotPassword: Boolean
        get() = submitState !is SubmitState.InProgress

    val passwordReadOnly: Boolean
        get() = submitState is SubmitState.InProgress

    val showSubmitInProgress: Boolean
        get() = submitState is SubmitState.InProgress
}

sealed interface LoginDestination {
    data class ForgotPassword(val email: String) : LoginDestination
    object Home : LoginDestination
}

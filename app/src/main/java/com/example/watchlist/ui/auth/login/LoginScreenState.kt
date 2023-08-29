package com.example.watchlist.ui.auth.login

import com.example.watchlist.data.model.SubmitState

data class LoginScreenState(
    val name: String,
    val password: String = "",
    val submitState: SubmitState<Unit> = SubmitState.Idle,
) {
    val enableSubmit: Boolean
        get() = password.isNotEmpty() && submitState !is SubmitState.InProgress

    val passwordReadOnly: Boolean
        get() = submitState is SubmitState.InProgress

    val showSubmitInProgress: Boolean
        get() = submitState is SubmitState.InProgress
}
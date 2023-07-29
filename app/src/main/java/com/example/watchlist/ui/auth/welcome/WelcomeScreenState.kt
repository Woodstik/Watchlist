package com.example.watchlist.ui.auth.welcome

import com.example.watchlist.data.enum.EmailStatus
import com.example.watchlist.data.model.SubmitState

data class WelcomeScreenState(
    val email: String = "",
    val emailStatus: EmailStatus = EmailStatus.NONE,
    val submitEmailState: SubmitState<Boolean> = SubmitState.Idle,
) {
    val enabledGoogle: Boolean
        get() = submitEmailState !is SubmitState.InProgress
    val enabledFacebook: Boolean
        get() = submitEmailState !is SubmitState.InProgress
    val emailReadOnly: Boolean
        get() = submitEmailState is SubmitState.InProgress
    val enableSubmitEmail: Boolean
        get() = emailStatus == EmailStatus.VALID &&
            submitEmailState !is SubmitState.InProgress
    val showSubmitEmailInProgress: Boolean
        get() = submitEmailState is SubmitState.InProgress
}

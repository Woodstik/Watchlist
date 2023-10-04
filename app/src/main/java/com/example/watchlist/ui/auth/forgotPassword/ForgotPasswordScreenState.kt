package com.example.watchlist.ui.auth.forgotPassword

import com.example.watchlist.data.enum.EmailStatus
import com.example.watchlist.data.model.SubmitState
import com.example.watchlist.data.response.ForgotPasswordResponse

data class ForgotPasswordScreenState(
    val email: String = "",
    val emailStatus: EmailStatus = EmailStatus.NONE,
    val sendEmailState: SendEmailState = SendEmailState.Allowed(false),
    val submitState: SubmitState<ForgotPasswordResponse> = SubmitState.Idle,
) {
    val emailReadOnly: Boolean
        get() = submitState == SubmitState.InProgress
    val enableSubmit: Boolean
        get() = emailStatus == EmailStatus.VALID &&
            sendEmailState is SendEmailState.Allowed
    val showProgress: Boolean
        get() = submitState == SubmitState.InProgress
}

sealed interface SendEmailState {
    data class Allowed(val isRetry: Boolean) : SendEmailState
    data class CoolDown(val timeRemaining: String) : SendEmailState
}

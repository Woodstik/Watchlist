package com.example.watchlist.ui.auth.signUp

import com.example.watchlist.data.model.SubmitState
import com.example.watchlist.data.response.SignUpResponse

data class SignUpScreenState(
    val email: String = "",
    val name: String = "",
    val password: String = "",
    val submitState: SubmitState<SignUpResponse> = SubmitState.Idle,
) {

    val enableSubmit: Boolean
        get() = name.isNotEmpty() && password.isNotEmpty() &&
            submitState !is SubmitState.InProgress

    val nameReadOnly: Boolean
        get() = submitState !is SubmitState.InProgress

    val passwordReadOnly: Boolean
        get() = submitState !is SubmitState.InProgress
}

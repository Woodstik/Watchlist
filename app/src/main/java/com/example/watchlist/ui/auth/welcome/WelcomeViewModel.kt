package com.example.watchlist.ui.auth.welcome

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableLiveData(WelcomeScreenState())
    val state: LiveData<WelcomeScreenState> = _state

    fun onEmailChange(newEmail: String) {
        _state.value = _state.value!!.copy(
            email = newEmail,
            emailError = getEmailError(newEmail),
            enabledContinue = isEmailComplete(newEmail),
        )
    }

    fun onClickContinueEmail() {
        if (!isEmailComplete(_state.value!!.email)) return
    }

    fun onClickContinueGoogle() {
    }

    fun onClickContinueFacebook() {
    }

    // TODO: Change this to a use case
    private fun getEmailError(email: String): EmailError {
        var error = EmailError.NONE
        if (email.trim().isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
            error = EmailError.INVALID_EMAIL
        }
        return error
    }

    private fun isEmailComplete(email: String): Boolean {
        return email.trim().isNotEmpty() && getEmailError(email) == EmailError.NONE
    }
}

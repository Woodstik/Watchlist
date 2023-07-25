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
        val error = getEmailError(newEmail.trim())
        _state.value = _state.value!!.copy(
            email = newEmail,
            emailError = error,
            enabledContinue = newEmail.isNotEmpty() && error == EmailError.NONE,
        )
    }

    fun onClickContinueEmail() {
    }

    fun onClickContinueGoogle() {
    }

    fun onClickContinueFacebook() {
    }

    // TODO: Change this to a use case
    private fun getEmailError(email: String): EmailError {
        var error = EmailError.NONE
        if (email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            error = EmailError.INVALID_EMAIL
        }
        return error
    }
}

package com.example.watchlist.ui.auth.welcome

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
            enabledContinue = newEmail.isNotEmpty(),
        )
    }

    fun onClickContinueEmail() {
    }

    fun onClickContinueGoogle() {
    }

    fun onClickContinueFacebook() {
    }
}

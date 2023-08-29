package com.example.watchlist.ui.auth.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.watchlist.ui.auth.signUp.SignUpScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = LoginArgs(savedStateHandle)

    private val _screenState = MutableStateFlow(
        LoginScreenState(name = args.name),
    )
    val screenState = _screenState.asStateFlow()

    fun onPasswordChange(newPassword: String) {
        _screenState.update {
            it.copy(password = newPassword)
        }
    }

    fun onClickSubmit() {
        //TODO: Implement submit use case
    }

    fun onClickForgotPassword() {
        //TODO: Implement submit use case
    }
}
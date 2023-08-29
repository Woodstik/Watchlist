package com.example.watchlist.ui.auth.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = LoginArgs(savedStateHandle)

    fun onPasswordChange(newPassword: String) {

    }

    fun onClickSubmit() {

    }

    fun onClickForgotPassword() {

    }
}
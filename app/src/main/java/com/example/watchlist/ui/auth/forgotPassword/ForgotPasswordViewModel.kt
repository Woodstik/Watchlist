package com.example.watchlist.ui.auth.forgotPassword

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = ForgotPasswordArgs(savedStateHandle)

    private val _screenState = MutableStateFlow(ForgotPasswordScreenState(email = args.email))
    val screenState = _screenState.asStateFlow()

    fun onSubmit() {
        TODO("Not yet implemented")
    }

    fun onEmailChange(newEmail: String) {
        TODO("Not yet implemented")
    }
}

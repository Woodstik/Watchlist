package com.example.watchlist.ui.auth.signUp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = SignUpArgs(savedStateHandle)

    private val _screenState = MutableStateFlow(SignUpScreenState(email = args.email))
    val screenState = _screenState.asStateFlow()

    fun onPasswordChange(password: String) {
        _screenState.update { it.copy(password = password) }
    }

    fun onNameChange(name: String) {
        _screenState.update { it.copy(name = name) }
    }
}

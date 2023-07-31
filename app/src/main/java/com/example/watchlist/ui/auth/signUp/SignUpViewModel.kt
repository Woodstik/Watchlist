package com.example.watchlist.ui.auth.signUp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlist.data.request.SignUpRequest
import com.example.watchlist.domain.SignUpUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val signUpUserUseCase: SignUpUserUseCase,
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

    fun signUp() {
        viewModelScope.launch {
            val state = _screenState.value
            signUpUserUseCase(
                SignUpRequest(
                    state.email,
                    state.name,
                    state.password,
                ),
            ).collectLatest {
                _screenState.update { currentState -> currentState.copy(submitState = it) }
            }
        }
    }
}

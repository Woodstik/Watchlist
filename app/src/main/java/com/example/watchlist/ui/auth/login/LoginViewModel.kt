package com.example.watchlist.ui.auth.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlist.data.model.SubmitState
import com.example.watchlist.data.request.LoginRequest
import com.example.watchlist.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val args = LoginArgs(savedStateHandle)

    private val _screenState = MutableStateFlow(
        LoginScreenState(name = args.name, email = args.email),
    )
    val screenState = _screenState.asStateFlow()
    private val _navState = MutableStateFlow<LoginDestination?>(null)
    val navState = _navState.asStateFlow()

    fun onPasswordChange(newPassword: String) {
        _screenState.update {
            it.copy(password = newPassword)
        }
    }

    fun onClickSubmit() {
        viewModelScope.launch {
            loginUseCase(
                LoginRequest(
                    email = _screenState.value.email,
                    password = _screenState.value.password,
                ),
            ).collectLatest {
                _screenState.update { currentState -> currentState.copy(submitState = it) }
                if (it is SubmitState.Success) {
                    if (it.data.isEmailVerified) {
                        _navState.update { LoginDestination.Home }
                    } else {
                        _navState.update { LoginDestination.VerifyEmail(_screenState.value.email) }
                    }
                }
            }
        }
    }

    fun onClickForgotPassword() {
        _navState.update { LoginDestination.ForgotPassword(_screenState.value.email) }
    }

    fun onNavigate() {
        _navState.update { null }
    }
}

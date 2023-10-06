package com.example.watchlist.ui.auth.signUp

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlist.data.model.SubmitState
import com.example.watchlist.data.request.SignUpRequest
import com.example.watchlist.domain.CheckPasswordRequirementsUseCase
import com.example.watchlist.domain.SignUpUseCase
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
    private val signUpUseCase: SignUpUseCase,
    private val checkPasswordRequirementsUseCase: CheckPasswordRequirementsUseCase,
) : ViewModel() {

    private val args = SignUpArgs(savedStateHandle)

    private val _screenState = MutableStateFlow(SignUpScreenState(email = args.email, passwordRequirements = checkPasswordRequirementsUseCase()))
    val screenState = _screenState.asStateFlow()
    private val _navState = MutableStateFlow<SignUpDestination?>(null)
    val navState = _navState.asStateFlow()

    fun onPasswordChange(password: String) {
        _screenState.update { it.copy(password = password, passwordRequirements = checkPasswordRequirementsUseCase(password)) }
    }

    fun onNameChange(name: String) {
        _screenState.update { it.copy(name = name) }
    }

    fun signUp() {
        viewModelScope.launch {
            val state = _screenState.value
            signUpUseCase(SignUpRequest(state.email, state.name, state.password)).collectLatest {
                _screenState.update { currentState -> currentState.copy(submitState = it) }
                if (it is SubmitState.Success) {
                    _navState.update { SignUpDestination.VerifyEmail(state.email) }
                }
            }
        }
    }

    fun onNavigate() {
        _screenState.update { SignUpScreenState(email = args.email, passwordRequirements = checkPasswordRequirementsUseCase()) }
        _navState.update { null }
    }
}

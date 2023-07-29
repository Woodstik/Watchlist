package com.example.watchlist.ui.auth.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlist.data.enum.EmailStatus
import com.example.watchlist.data.model.SubmitState
import com.example.watchlist.domain.CheckEmailValidUseCase
import com.example.watchlist.domain.SubmitUserEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val checkEmailValidUseCase: CheckEmailValidUseCase,
    private val submitUserEmailUseCase: SubmitUserEmailUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(WelcomeScreenState())
    val state: StateFlow<WelcomeScreenState> = _state
    private val _navState = MutableStateFlow<WelcomeNavDestination?>(null)
    val navState: StateFlow<WelcomeNavDestination?> = _navState

    fun onEmailChange(newEmail: String) {
        _state.value = _state.value.copy(
            email = newEmail,
            emailStatus = checkEmailValidUseCase(newEmail),
        )
    }

    fun onClickContinueEmail() {
        val emailStatus = checkEmailValidUseCase(_state.value.email)
        if (emailStatus != EmailStatus.VALID) return
        viewModelScope.launch {
            submitUserEmailUseCase(_state.value.email).collectLatest {
                _state.value = _state.value.copy(
                    submitEmailState = it,
                )
                if (it is SubmitState.Success) {
                    _navState.value = WelcomeNavDestination.SignUp(_state.value.email)
                }
            }
        }
    }

    fun onClickContinueGoogle() {
    }

    fun onClickContinueFacebook() {
    }

    fun onNavigate() {
        _navState.value = null
    }
}

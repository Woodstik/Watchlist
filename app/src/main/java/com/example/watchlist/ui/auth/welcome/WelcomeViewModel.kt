package com.example.watchlist.ui.auth.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlist.data.enum.EmailStatus
import com.example.watchlist.data.model.SubmitState
import com.example.watchlist.domain.CheckEmailValidUseCase
import com.example.watchlist.domain.SubmitUserEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val checkEmailValidUseCase: CheckEmailValidUseCase,
    private val submitUserEmailUseCase: SubmitUserEmailUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(WelcomeScreenState())
    val state: Flow<WelcomeScreenState> = _state

    fun onEmailChange(newEmail: String) {
        val emailStatus = checkEmailValidUseCase(newEmail)
        val oldEmailFormState = _state.value.emailFormState
        val newEmailFormState = oldEmailFormState.copy(
            email = newEmail,
            emailStatus = emailStatus,
            enabledContinue = emailStatus == EmailStatus.VALID,
        )
        _state.value = _state.value.copy(emailFormState = newEmailFormState)
    }

    fun onClickContinueEmail() {
        val emailFormState = _state.value.emailFormState
        val emailStatus = checkEmailValidUseCase(emailFormState.email)
        if (emailStatus != EmailStatus.VALID) return
        viewModelScope.launch {
            submitUserEmailUseCase(emailFormState.email).collectLatest { handleSubmitEmailState(it) }
        }
    }

    fun onClickContinueGoogle() {
    }

    fun onClickContinueFacebook() {
    }

    private fun handleSubmitEmailState(state: SubmitState<Boolean>) {
        val emailFormState = _state.value.emailFormState
        _state.value = _state.value.copy(
            enabledFacebook = state is SubmitState.Error,
            enabledGoogle = state is SubmitState.Error,
            emailFormState = emailFormState.copy(
                enabledContinue = state is SubmitState.Error,
                showContinueInProgress = state is SubmitState.InProgress,
                emailEnabled = state is SubmitState.Error,
            ),
        )
    }
}

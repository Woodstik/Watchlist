package com.example.watchlist.ui.auth.welcome

import androidx.lifecycle.ViewModel
import com.example.watchlist.data.enums.EmailStatus
import com.example.watchlist.domain.CheckEmailValidUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val checkEmailValidUseCase: CheckEmailValidUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(WelcomeScreenState())
    val state: Flow<WelcomeScreenState> = _state

    fun onEmailChange(newEmail: String) {
        val emailStatus = checkEmailValidUseCase(newEmail)
        _state.value = _state.value.copy(
            email = newEmail,
            emailStatus = emailStatus,
            enabledContinue = emailStatus == EmailStatus.VALID,
        )
    }

    fun onClickContinueEmail() {
        val emailStatus = checkEmailValidUseCase(_state.value.email)
        if (emailStatus != EmailStatus.VALID) return
    }

    fun onClickContinueGoogle() {
    }

    fun onClickContinueFacebook() {
    }
}

package com.example.watchlist.ui.auth.forgotPassword

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlist.data.model.SubmitState
import com.example.watchlist.data.request.ForgotPasswordRequest
import com.example.watchlist.domain.CheckEmailValidUseCase
import com.example.watchlist.domain.CountDownTimerUseCase
import com.example.watchlist.domain.UserForgotPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val checkEmailValidUseCase: CheckEmailValidUseCase,
    private val forgotPasswordUseCase: UserForgotPasswordUseCase,
    private val countDownTimerUseCase: CountDownTimerUseCase,
) : ViewModel() {

    companion object {
        private const val COOL_DOWN_TIME = 45
    }

    private val args = ForgotPasswordArgs(savedStateHandle)

    private val _screenState = MutableStateFlow(
        ForgotPasswordScreenState(
            email = args.email,
            emailStatus = checkEmailValidUseCase(args.email),
        ),
    )
    val screenState = _screenState.asStateFlow()

    fun onSubmit() {
        if (screenState.value.sendEmailState is SendEmailState.CoolDown) return
        viewModelScope.launch {
            forgotPasswordUseCase(ForgotPasswordRequest(_screenState.value.email)).collectLatest {
                _screenState.update { currentState ->
                    currentState.copy(
                        submitState = it,
                        sendEmailState = when (it) {
                            is SubmitState.Error -> SendEmailState.Allowed(true)
                            else -> currentState.sendEmailState
                        },
                    )
                }
                if (it is SubmitState.Success) {
                    startCoolDownTimer()
                }
            }
        }
    }

    fun onEmailChange(newEmail: String) {
        _screenState.update { currentState ->
            currentState.copy(
                email = newEmail,
                emailStatus = checkEmailValidUseCase(newEmail),
            )
        }
    }

    private fun startCoolDownTimer() {
        viewModelScope.launch {
            countDownTimerUseCase(COOL_DOWN_TIME).map { secondsLeft ->
                val minutes = TimeUnit.SECONDS.toMinutes(secondsLeft.toLong())
                val seconds = secondsLeft - TimeUnit.MINUTES.toSeconds(minutes)
                String.format("%02d:%02d", minutes, seconds)
            }.collectLatest {
                _screenState.update { currentState -> currentState.copy(sendEmailState = SendEmailState.CoolDown(it)) }
            }
            _screenState.update { currentState -> currentState.copy(sendEmailState = SendEmailState.Allowed(true)) }
        }
    }
}

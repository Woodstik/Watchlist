package com.example.watchlist.ui.auth.verifyEmail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class VerifyEmailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args = VerifyEmailArgs(savedStateHandle)

    private val _screenState = MutableStateFlow(VerifyEmailScreenState(email = args.email))
    val screenState = _screenState.asStateFlow()

    private val _navState = MutableStateFlow<VerifyEmailDestination?>(null)
    val navState = _navState.asStateFlow()

    fun onNavigate() {
        _navState.update { null }
    }
}

package com.example.watchlist.ui.auth.signUp

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    private val _screenState = MutableStateFlow(SignUpScreenState())
    val screenState = _screenState.asStateFlow()
}

package com.example.watchlist.ui.auth.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {

    private val _state = MutableLiveData(WelcomeScreenState())
    val state: LiveData<WelcomeScreenState> = _state
}

package com.example.watchlist.data.model

import com.example.watchlist.data.enum.ErrorType

sealed class SubmitState<out T> {
    object Idle : SubmitState<Nothing>()
    object InProgress : SubmitState<Nothing>()
    data class Success<out T>(val data: T) : SubmitState<T>()
    data class Error(val type: ErrorType) : SubmitState<Nothing>()
}

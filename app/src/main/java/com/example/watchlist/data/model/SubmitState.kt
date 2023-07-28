package com.example.watchlist.data.model

sealed class SubmitState<out T> {
    object InProgress : SubmitState<Nothing>()
    data class Success<out T>(val data: T) : SubmitState<T>()
    data class Error(val type: String) : SubmitState<Nothing>()
}

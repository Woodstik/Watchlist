package com.example.watchlist.data.response

data class SubmitUserEmailResponse(
    val email: String,
    val hasAccount: Boolean,
    val name: String,
)

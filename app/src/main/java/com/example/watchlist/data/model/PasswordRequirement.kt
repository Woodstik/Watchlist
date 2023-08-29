package com.example.watchlist.data.model

import com.example.watchlist.data.enum.PasswordRequirementCode

data class PasswordRequirement(
    val code: PasswordRequirementCode,
    val passed: Boolean = false,
) {
    companion object {
        const val MIN_LENGTH = 8
    }
}

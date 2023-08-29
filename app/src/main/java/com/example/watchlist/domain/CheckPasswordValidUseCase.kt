package com.example.watchlist.domain

import com.example.watchlist.data.enum.PasswordRequirementCode
import com.example.watchlist.data.model.PasswordRequirement
import javax.inject.Inject

class CheckPasswordValidUseCase @Inject constructor() {
    operator fun invoke(password: String): List<PasswordRequirement> {
        return PasswordRequirementCode.values().map { checkRequirement(password, it) }
    }

    private fun checkRequirement(
        password: String,
        code: PasswordRequirementCode,
    ): PasswordRequirement {
        val passed = when (code) {
            PasswordRequirementCode.NUMBERS -> password.contains("\\d".toRegex())
            PasswordRequirementCode.LOWERCASE -> password.contains("[a-zà-ü]".toRegex())
            PasswordRequirementCode.UPPERCASE -> password.contains("[A-ZÀ-Ü]".toRegex())
            PasswordRequirementCode.SYMBOL -> password.contains("[^a-zA-Zà-üÀ-Ü0-9\\s]".toRegex())
            PasswordRequirementCode.MIN_LENGTH -> password.length >= PasswordRequirement.MIN_LENGTH
        }
        return PasswordRequirement(code, passed)
    }
}

package com.example.watchlist.domain

import android.util.Patterns
import com.example.watchlist.data.enum.EmailStatus
import javax.inject.Inject

class CheckEmailValidUseCase @Inject constructor() {
    operator fun invoke(email: String): EmailStatus {
        val trimmedEmail = email.trim()
        return if (trimmedEmail.isEmpty()) {
            EmailStatus.NONE
        } else if (!Patterns.EMAIL_ADDRESS.matcher(trimmedEmail).matches()) {
            EmailStatus.INVALID
        } else {
            EmailStatus.VALID
        }
    }
}

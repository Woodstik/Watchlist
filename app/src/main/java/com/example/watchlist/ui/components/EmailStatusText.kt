package com.example.watchlist.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.watchlist.R
import com.example.watchlist.data.enum.EmailStatus

@Composable
fun EmailStatusText(status: EmailStatus) {
    when (status) {
        EmailStatus.INVALID -> Text(stringResource(id = R.string.form_error_invalid_email))
        else -> Text("")
    }
}

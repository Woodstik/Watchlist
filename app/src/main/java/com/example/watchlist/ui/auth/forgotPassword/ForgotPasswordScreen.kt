@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.watchlist.ui.auth.forgotPassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.watchlist.R
import com.example.watchlist.data.enum.EmailStatus
import com.example.watchlist.ui.components.EmailStatusText
import com.example.watchlist.ui.components.Toolbar
import com.example.watchlist.ui.theme.WatchlistTheme
import com.example.watchlist.ui.theme.spacing

@Composable
fun ForgotPasswordScreen(
    state: ForgotPasswordScreenState = ForgotPasswordScreenState(),
    onEmailChange: (String) -> Unit = {},
    onClickSubmit: () -> Unit = {},
    onClickBack: () -> Unit = {},
) {
    Scaffold(
        topBar = { Toolbar(onClickBack = onClickBack) },
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = MaterialTheme.spacing.medium),
        ) {
            ForgotPasswordScreenInfo()
            ForgotPasswordForm(
                email = state.email,
                emailStatus = state.emailStatus,
                emailReadOnly = state.emailReadOnly,
                showProgress = state.showProgress,
                enableSubmit = state.enableSubmit,
                sendEmailState = state.sendEmailState,
                onEmailChange = onEmailChange,
                onClickSubmit = onClickSubmit,
            )
        }
    }
}

@Composable
private fun ForgotPasswordScreenInfo() {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xSmall),
    ) {
        Text(
            text = stringResource(id = R.string.forgot_password_screen_title),
            style = MaterialTheme.typography.headlineLarge,
        )
        Text(
            text = stringResource(id = R.string.forgot_password_screen_message),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
private fun ForgotPasswordForm(
    email: String,
    emailStatus: EmailStatus,
    emailReadOnly: Boolean,
    showProgress: Boolean,
    enableSubmit: Boolean,
    sendEmailState: SendEmailState,
    onEmailChange: (String) -> Unit,
    onClickSubmit: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = email,
            label = { Text(stringResource(id = R.string.input_label_email)) },
            placeholder = { Text(stringResource(id = R.string.input_placeholder_email)) },
            supportingText = { EmailStatusText(emailStatus) },
            isError = emailStatus == EmailStatus.INVALID,
            onValueChange = { onEmailChange(it) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            readOnly = emailReadOnly,
            keyboardActions = KeyboardActions {
                onClickSubmit()
                focusManager.clearFocus()
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Go,
                keyboardType = KeyboardType.Email,
            ),
        )
        if (showProgress) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(ButtonDefaults.MinHeight),
            )
        } else {
            Button(
                onClick = onClickSubmit,
                modifier = Modifier.fillMaxWidth(),
                enabled = enableSubmit,
            ) {
                Text(
                    when {
                        sendEmailState is SendEmailState.Allowed && !sendEmailState.isRetry -> stringResource(R.string.btn_send_email)
                        sendEmailState is SendEmailState.CoolDown -> stringResource(
                            R.string.btn_resend_email_cool_down,
                            sendEmailState.timeRemaining,
                        )
                        else -> stringResource(R.string.btn_resend_email)
                    },
                )
            }
        }
    }
}

@Preview
@Composable
private fun ForgotPasswordScreenDarkPreview() {
    WatchlistTheme(darkTheme = true) {
        ForgotPasswordScreen(
            state = ForgotPasswordScreenState(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ForgotPasswordScreenLightPreview() {
    WatchlistTheme {
        ForgotPasswordScreen(
            state = ForgotPasswordScreenState(),
        )
    }
}

@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.watchlist.ui.auth.welcome

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.watchlist.R
import com.example.watchlist.data.enum.EmailStatus
import com.example.watchlist.ui.components.EmailStatusText
import com.example.watchlist.ui.theme.WatchlistTheme
import com.example.watchlist.ui.theme.spacing

@Composable
fun WelcomeScreen(
    state: WelcomeScreenState = WelcomeScreenState(),
    onEmailChange: (String) -> Unit = {},
    onClickContinueEmail: () -> Unit = {},
    onClickContinueGoogle: () -> Unit = {},
    onClickContinueFacebook: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.padding(MaterialTheme.spacing.medium),
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large),
            modifier = Modifier.padding(paddingValues),
        ) {
            Box(modifier = Modifier.weight(1f)) // TODO: Add something cooler than blank space
            WelcomeScreenInfo()
            EmailForm(
                email = state.email,
                emailStatus = state.emailStatus,
                emailReadOnly = state.emailReadOnly,
                showProgress = state.showSubmitEmailInProgress,
                enableSubmit = state.enableSubmitEmail,
                onEmailChange = { onEmailChange(it) },
                onClickSubmit = { onClickContinueEmail() },
            )
            Text(
                text = stringResource(id = R.string.welcome_screen_or),
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
            ) {
                SocialButton(
                    onClick = { onClickContinueGoogle() },
                    iconId = R.drawable.ic_google,
                    text = stringResource(id = R.string.btn_continue_with_google),
                    iconContentDescription = R.string.content_description_google,
                    enabled = state.enabledGoogle,
                )
                SocialButton(
                    onClick = { onClickContinueFacebook() },
                    iconId = R.drawable.ic_facebook,
                    text = stringResource(id = R.string.btn_continue_with_facebook),
                    iconContentDescription = R.string.content_description_facebook,
                    enabled = state.enabledFacebook,
                )
            }
        }
    }
}

@Composable
private fun WelcomeScreenInfo() {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xSmall),
    ) {
        Text(
            text = stringResource(id = R.string.welcome_screen_title),
            style = MaterialTheme.typography.headlineLarge,
        )
        Text(
            text = stringResource(id = R.string.welcome_screen_message),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
private fun EmailForm(
    email: String,
    emailStatus: EmailStatus,
    emailReadOnly: Boolean,
    enableSubmit: Boolean,
    showProgress: Boolean,
    onEmailChange: (String) -> Unit,
    onClickSubmit: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xSmall),
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
                Text(stringResource(id = R.string.btn_continue))
            }
        }
    }
}

@Composable
private fun SocialButton(
    onClick: () -> Unit,
    @DrawableRes iconId: Int,
    text: String,
    @StringRes iconContentDescription: Int,
    enabled: Boolean,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = stringResource(id = iconContentDescription),
                modifier = Modifier.align(Alignment.CenterStart),
            )
            Text(text, modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview()
@Composable
private fun WelcomeScreenDarkPreview() {
    WatchlistTheme(darkTheme = true) { WelcomeScreen() }
}

@Preview()
@Composable
private fun WelcomeScreenLightPreview() {
    WatchlistTheme { WelcomeScreen() }
}

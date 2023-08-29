@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.watchlist.ui.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.watchlist.R
import com.example.watchlist.ui.components.PasswordTextField
import com.example.watchlist.ui.components.Toolbar
import com.example.watchlist.ui.theme.WatchlistTheme
import com.example.watchlist.ui.theme.spacing

@Composable
fun LoginScreen(
    state: LoginScreenState = LoginScreenState(),
    onPasswordChange: (String) -> Unit = {},
    onClickForgotPassword: () -> Unit = {},
    onClickSubmit: () -> Unit = {},
    onClickBack: () -> Unit = {},
) {
    Scaffold(
        topBar = { Toolbar(onClickBack = onClickBack) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        ) {
            LoginScreenInfo(email = state.email, name = state.name)
            LoginForm(
                password = state.password,
                passwordReadOnly = state.passwordReadOnly,
                onPasswordChange = onPasswordChange,
                onClickForgotPassword = onClickForgotPassword,
                enableForgotPassword = state.enableForgotPassword,
                onClickSubmit = onClickSubmit,
                enableSubmit = state.enableSubmit,
                showProgress = state.showSubmitInProgress,
            )
        }
    }
}

@Composable
private fun LoginScreenInfo(
    email: String,
    name: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
    ) {
        Text(
            text = stringResource(id = R.string.sign_up_screen_title),
            style = MaterialTheme.typography.headlineLarge,
        )
        UserAccountInfo(email = email, name = name)
    }
}

@Composable
private fun UserAccountInfo(
    email: String,
    name: String,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(50),
                ),
        ) {
            Text(
                text = name.firstOrNull().toString(),
                color = MaterialTheme.colorScheme.onSecondary,
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xSmall),
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = email,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Composable
private fun LoginForm(
    password: String,
    passwordReadOnly: Boolean,
    onPasswordChange: (String) -> Unit,
    onClickForgotPassword: () -> Unit,
    enableForgotPassword: Boolean,
    enableSubmit: Boolean,
    onClickSubmit: () -> Unit,
    showProgress: Boolean,
) {
    val focusManager = LocalFocusManager.current
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PasswordTextField(
            value = password,
            onValueChange = onPasswordChange,
            readOnly = passwordReadOnly,
            modifier = Modifier.fillMaxWidth(),
            imeAction = ImeAction.Go,
            keyboardActions = KeyboardActions {
                onClickSubmit()
                focusManager.clearFocus()
            },
        )

        TextButton(
            onClick = onClickForgotPassword,
            enabled = enableForgotPassword,
        ) {
            Text(stringResource(id = R.string.btn_forgot_password))
        }

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
                Text(stringResource(id = R.string.btn_login))
            }
        }
    }
}

@Preview()
@Composable
private fun LoginScreenDarkPreview() {
    WatchlistTheme(darkTheme = true) {
        LoginScreen(
            state = LoginScreenState(
                name = "John Doe",
                email = "example@email.com",
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenLightPreview() {
    WatchlistTheme {
        LoginScreen(
            state = LoginScreenState(
                name = "John Doe",
                email = "example@email.com",
            ),
        )
    }
}

@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.watchlist.ui.auth.signUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.watchlist.R
import com.example.watchlist.ui.theme.WatchlistTheme
import com.example.watchlist.ui.theme.spacing

@Composable
fun SignUpScreen(
    state: SignUpScreenState = SignUpScreenState(),
    onNameChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onClickSubmit: () -> Unit = {},
    onClickBack: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { onClickBack() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.content_description_back),
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = MaterialTheme.spacing.medium),
        ) {
            SignUpScreenInfo(state.email)
            SignUpForm(
                name = state.name,
                nameReadOnly = state.nameReadOnly,
                password = state.password,
                passwordReadOnly = state.passwordReadOnly,
                enableSubmit = state.enableSubmit,
                showProgress = state.showSubmitInProgress,
                onNameChange = onNameChange,
                onPasswordChange = onPasswordChange,
                onClickSubmit = onClickSubmit,
            )
        }
    }
}

@Composable
private fun SignUpScreenInfo(
    email: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xSmall),
    ) {
        Text(
            text = stringResource(id = R.string.sign_up_screen_title),
            style = MaterialTheme.typography.headlineLarge,
        )
        Text(
            text = stringResource(id = R.string.sign_up_screen_message, email),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
private fun SignUpForm(
    name: String,
    nameReadOnly: Boolean,
    password: String,
    passwordReadOnly: Boolean,
    enableSubmit: Boolean,
    showProgress: Boolean,
    onNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onClickSubmit: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
    ) {
        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        OutlinedTextField(
            value = name,
            label = { Text(stringResource(id = R.string.input_label_name)) },
            placeholder = { Text(stringResource(id = R.string.input_placeholder_name)) },
            onValueChange = { onNameChange(it) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            readOnly = nameReadOnly,
            keyboardActions = KeyboardActions { focusManager.moveFocus(FocusDirection.Next) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )
        OutlinedTextField(
            value = password,
            label = { Text(stringResource(id = R.string.input_label_password)) },
            onValueChange = { onPasswordChange(it) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            readOnly = passwordReadOnly,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = passwordVisible.not() }) {
                    val icon = if (passwordVisible) {
                        painterResource(R.drawable.ic_show_password)
                    } else {
                        painterResource(R.drawable.ic_hide_password)
                    }
                    val contentDescription = if (passwordVisible) {
                        stringResource(id = R.string.content_description_hide_password)
                    } else {
                        stringResource(id = R.string.content_description_show_password)
                    }
                    Icon(painter = icon, contentDescription = contentDescription)
                }
            },
            keyboardActions = KeyboardActions {
                onClickSubmit()
                focusManager.clearFocus()
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Go,
                keyboardType = KeyboardType.Password,
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
                Text(stringResource(id = R.string.btn_sign_up))
            }
        }
    }
}

@Preview()
@Composable
private fun SignUpScreenDarkPreview() {
    WatchlistTheme(darkTheme = true) { SignUpScreen() }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenLightPreview() {
    WatchlistTheme { SignUpScreen() }
}

@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.watchlist.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.watchlist.R

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    imeAction: ImeAction = ImeAction.Default,
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        label = { Text(stringResource(id = R.string.input_label_password)) },
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        singleLine = true,
        readOnly = readOnly,
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
        keyboardActions = keyboardActions,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction,
        ),
    )
}

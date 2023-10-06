@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.watchlist.ui.auth.verifyEmail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.watchlist.ui.components.Toolbar
import com.example.watchlist.ui.theme.spacing

@Composable
fun VerifyEmailScreen(
    state: VerifyEmailScreenState = VerifyEmailScreenState(),
    onClickBack: () -> Unit,
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
            Text(text = "Verify Email: ${state.email}")
        }
    }
}

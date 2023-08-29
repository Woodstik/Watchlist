@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.watchlist.ui.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.watchlist.R

@Composable
fun Toolbar(
    onClickBack: () -> Unit = {},
) {
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
}

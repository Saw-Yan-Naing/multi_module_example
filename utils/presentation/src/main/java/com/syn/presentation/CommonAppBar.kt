package com.syn.presentation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonAppBar(
    title: String,
    modifier: Modifier = Modifier,
    customBackButton: @Composable (() -> Unit)? = null,
    actions : @Composable (RowScope.() -> Unit)? = null,
    colors: TopAppBarColors = TopAppBarDefaults. topAppBarColors(),
    onBackPressed: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            customBackButton?.invoke() ?: CommonIconButton(
                onClick = onBackPressed,
                icon = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = "Back"
            )
        },
        title = {
            CommonText(
                text = title,
                modifier = Modifier
            )
        },
        actions ={
            actions?.invoke(this)
        },
        colors = colors
    )
}
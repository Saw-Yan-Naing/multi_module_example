package com.syn.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.syn.function.check

@Composable
fun CommonScaffold(
    modifier: Modifier = Modifier,
    appBar: (@Composable () -> Unit)? = null,
    bottomBar: (@Composable () -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            appBar?.invoke()
        },
        bottomBar = {
            bottomBar?.invoke()
        }
    ) { paddingValue ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(
                    top = ((appBar != null) check { paddingValue.calculateTopPadding() }) ?: 0.dp,
                    bottom = ((bottomBar != null) check { paddingValue.calculateBottomPadding() })
                        ?: 0.dp
                ),
            content = content
        )
    }
}
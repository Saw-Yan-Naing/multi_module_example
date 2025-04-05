package com.syn.presentation

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CommonIconButton(
    modifier: Modifier = Modifier,
    icon: Any ? = null,
    contentDescription: String ? = null,
    onClick: () -> Unit = {  },
){
    IconButton(
        onClick= onClick,
        modifier = modifier,
        content = {
            icon?.let {
                CommonIcon(
                    icon = it,
                    contentDescription = contentDescription
                )
            }
        }
    )
}
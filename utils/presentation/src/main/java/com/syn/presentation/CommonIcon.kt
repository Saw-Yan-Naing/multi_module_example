package com.syn.presentation

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

@Composable
fun CommonIcon(
    modifier: Modifier = Modifier,
    icon: Any,
    contentDescription: String? = null,
) {
    when(icon){
        is ImageVector -> {
            Icon(
                imageVector = icon as ImageVector,
                contentDescription = contentDescription,
                modifier = modifier
            )
        }

        is Int -> {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = contentDescription,
                modifier = modifier
            )
        }

        else -> {
            // Handle other types if needed
        }
    }

}
package com.syn.presentation

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import android.app.Activity
import android.view.View

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeholder: String? = null,
    isError: Boolean = false,
    isEnabled: Boolean = true,
    isSingleLine: Boolean = true,
    maxLines: Int = 1,
    trailingIcon: (@Composable () -> Unit)? = null,
) {
    val focusManager = LocalFocusManager.current
    var textFieldValue by remember { mutableStateOf(TextFieldValue(value)) }

    BasicTextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onValueChange(it.text)
        },
        modifier = modifier,
        singleLine = isSingleLine,
        maxLines = maxLines,
        enabled = isEnabled,
        decorationBox = { innerTextField ->
            // Add your decoration here (label, placeholder, error, etc.)
            innerTextField()

            // Optionally add a trailing icon
            trailingIcon?.invoke()

            // Handle error state
            if (isError) {
                // Show error indication (e.g., red border)
            }

            // Handle label and placeholder
            label?.let {
                // Show label
            }

            placeholder?.let {
                // Show placeholder
            }


        }
    )

    // Clear focus when the keyboard is hidden
    DisposableEffect(Unit) {
        val keyboardVisibilityListener = object : KeyboardVisibilityListener {
            override fun onKeyboardVisibilityChanged(isVisible: Boolean) {
                if (!isVisible) {
                    focusManager.clearFocus()
                }
            }
        }
        KeyboardVisibilityObserver.registerListener(keyboardVisibilityListener)
        onDispose {
            KeyboardVisibilityObserver.unregisterListener(keyboardVisibilityListener)
        }
    }
}

interface KeyboardVisibilityListener {
    fun onKeyboardVisibilityChanged(isVisible: Boolean)
}

object KeyboardVisibilityObserver {
    private val listeners = mutableListOf<KeyboardVisibilityListener>()

    fun registerListener(listener: KeyboardVisibilityListener) {
        listeners.add(listener)
    }

    fun unregisterListener(listener: KeyboardVisibilityListener) {
        listeners.remove(listener)
    }

    fun setup(activity: Activity) {
        val rootView: View = activity.window.decorView.findViewById(android.R.id.content)
        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = android.graphics.Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.height
            val keypadHeight = screenHeight - rect.bottom
            val isVisible = keypadHeight > screenHeight * 0.15
            listeners.forEach { it.onKeyboardVisibilityChanged(isVisible) }
        }
    }
}
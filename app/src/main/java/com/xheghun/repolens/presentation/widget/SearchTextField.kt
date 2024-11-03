package com.xheghun.repolens.presentation.widget

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.xheghun.repolens.presentation.theme.Grey

@Composable
fun SearchTextField(hintText: String, value: String, onValueChange: (String) -> Unit, modifier: Modifier) {
    BasicTextField(
        value = value,
        onValueChange = { newValue -> onValueChange(newValue) },
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(
                    text = hintText,
                    color = Grey,
                    style = MaterialTheme.typography.bodySmall,
                )
            } else {
                innerTextField()
            }

        },
        modifier = modifier
    )
}
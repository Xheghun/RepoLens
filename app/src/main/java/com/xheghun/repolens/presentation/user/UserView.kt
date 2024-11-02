package com.xheghun.repolens.presentation.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserView() {
    Column {
        androidx.compose.material3.Text(
            text = "Users",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 25.dp)
        )
    }
}
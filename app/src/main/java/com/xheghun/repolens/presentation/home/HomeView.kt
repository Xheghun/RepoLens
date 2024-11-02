package com.xheghun.repolens.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeView() {
    Column {
        Text(
            text = "Home",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 25.dp)
        )
    }
}
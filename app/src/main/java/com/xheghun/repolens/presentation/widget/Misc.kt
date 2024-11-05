package com.xheghun.repolens.presentation.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xheghun.repolens.presentation.theme.Black

@Composable
fun PageTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .padding(vertical = 25.dp)
    )
}


@Composable
fun screenWidthPercentage(p: Float): Dp {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val fifteenPercentWidth = screenWidth * p
    return fifteenPercentWidth
}

@Composable
fun ProgressIndicator() {
    CircularProgressIndicator(
        color = Black,
    )
}
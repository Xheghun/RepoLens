package com.xheghun.repolens.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.xheghun.repolens.presentation.theme.GreyLight

@Composable
fun NavTile(
    title: String,
    iconResource: Int,
    iconTint: Color,
    backgroundColor: Color,
    modifier: Modifier,
    onTap: () -> Unit,
    ) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier =
        modifier
            .height(140.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable { onTap() }
            .border(0.5.dp, GreyLight, shape = RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .padding(10.dp)
    ) {
        Icon(
            painter = painterResource(id = iconResource),
            contentDescription = "$title tile",
            tint = iconTint,
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(Color.White)
                .padding(12.dp)
        )
        Text(text = title, style = MaterialTheme.typography.titleMedium)
    }
}
package com.xheghun.repolens.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.xheghun.repolens.R
import com.xheghun.repolens.presentation.theme.Black
import com.xheghun.repolens.presentation.theme.Grey

@Composable
fun SearchBar(
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    onSearchPressed: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
        Modifier
            .fillMaxWidth()
            .border(1.dp, Black, RoundedCornerShape(6.dp))
            .padding(4.dp)
    ) {
        Icon(
            painterResource(id = R.drawable.search),
            contentDescription = "search prefix",
            tint = Grey,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(14.dp)
        )
        Box(Modifier.width(6.dp))
        SearchTextField(
            hintText = hint,
            value = value,
            onValueChange = onValueChange::invoke,
            modifier = Modifier.weight(1f)
        )
        Box(Modifier.width(6.dp))
        Text(
            "Search",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(Black)
                .clickable {
                    onSearchPressed.invoke()
                }
                .padding(horizontal = 25.dp, vertical = 10.dp)
        )
    }
}
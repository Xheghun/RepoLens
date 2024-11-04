package com.xheghun.repolens.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.xheghun.repolens.R
import com.xheghun.repolens.presentation.theme.GreyLight
import com.xheghun.repolens.presentation.theme.Teal

@Composable
fun UserItem(onTap: () -> Unit) {
    Surface(
        shadowElevation = 8.dp,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .border(0.5.dp, GreyLight, RoundedCornerShape(4.dp))
            .clip(RoundedCornerShape(4.dp))
            .clickable { onTap.invoke() }
    ) {
        Row( Modifier
            .padding(8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.repo_img),
                contentDescription = "user profile image",
                modifier = Modifier
                    .size(30.dp)
                    .clip(
                        CircleShape
                    )
            )

            Box(Modifier.width(10.dp))

            Column {
                Text(
                    text = "Paige Brown",
                    color = Teal,
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "DynamicWebPaige",
                    style = MaterialTheme.typography.bodySmall
                )

                Text(
                    "These are random words that will be replaced in due time. Config files for my github profile",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Row {
                    Text(
                        "Lagos,Nigeria",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Box(Modifier.width(10.dp))
                    Text(
                        "momoko@gmail.com",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
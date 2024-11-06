package com.xheghun.repolens.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.xheghun.repolens.R
import com.xheghun.repolens.data.models.User
import com.xheghun.repolens.presentation.theme.GreyLight
import com.xheghun.repolens.presentation.theme.Teal

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun UserItem(user: User, onTap: () -> Unit) {
    Surface(
        shadowElevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .border(0.5.dp, GreyLight, RoundedCornerShape(4.dp))
            .clip(RoundedCornerShape(4.dp))
            .clickable { onTap.invoke() }
    ) {
        Row(
            modifier =
            Modifier
                .padding(8.dp)
        ) {
            AsyncImage(
                error = painterResource(R.drawable.repo_img),
                placeholder = painterResource(R.drawable.repo_img),
                model = "${user.avatarURL}",
                contentDescription = "user profile image",
                modifier = Modifier
                    .size(30.dp)
                    .clip(
                        CircleShape
                    )
            )

            Box(Modifier.width(10.dp))

            Column(Modifier.align(Alignment.CenterVertically)) {
                user.name.takeIf { !it.isNullOrEmpty() }?.let {
                    Text(
                        text = it,
                        color = Teal,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                user.login?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }

                user.bio.takeIf { !it.isNullOrEmpty() }?.let {
                    Text(
                        it,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                FlowRow {
                    user.location.takeIf { !it.isNullOrEmpty() }?.let {
                        Text(
                            it,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodySmall
                        )

                        Box(Modifier.width(10.dp))
                    }

                    user.email.takeIf { !it.isNullOrEmpty() }?.let {
                        Text(
                            it,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}
package com.xheghun.repolens.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.xheghun.repolens.R
import com.xheghun.repolens.data.models.Repo
import com.xheghun.repolens.presentation.theme.Black
import com.xheghun.repolens.presentation.theme.DeepPurple
import com.xheghun.repolens.presentation.theme.Grey
import com.xheghun.repolens.presentation.theme.GreyLight
import com.xheghun.repolens.presentation.theme.Lime
import com.xheghun.repolens.presentation.theme.Teal
import com.xheghun.repolens.presentation.theme.TealTransparent
import timeAgo

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RepoItem(repo: Repo, userRepo: Boolean = false) {
    Surface(
        shadowElevation = 8.dp,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .border(0.5.dp, GreyLight, RoundedCornerShape(4.dp))
            .clip(RoundedCornerShape(4.dp))
    ) {
        Column(
            Modifier
                .padding(8.dp)

        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    error = painterResource(R.drawable.repo_img),
                    placeholder = painterResource(R.drawable.repo_img),
                    model = "${repo.owner?.avatarURL}",
                    contentDescription = "repo-image",
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                )

                Box(Modifier.width(10.dp))

                FlowRow(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "${repo.owner?.login}/",
                        color = DeepPurple,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    repo.name?.let {
                        Text(
                            text = it,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 12.sp),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }

                    if (userRepo) {
                        repo.private?.let { private ->
                            Text(
                                if (private) "Private" else "Public",
                                style = MaterialTheme.typography.displaySmall,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .border(0.5.dp, GreyLight, RoundedCornerShape(4.dp))
                                    .padding(vertical = 3.dp, horizontal = 8.dp)
                            )
                        }
                    }
                }

                Box(Modifier.width(4.dp))

                TextDrawable(text = "${repo.stargazersCount}") {
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "github star"
                    )
                }
                Box(Modifier.width(10.dp))
                repo.language?.let {
                    TextDrawable(text = it) {
                        Box(
                            Modifier
                                .size(12.dp)
                                .clip(CircleShape)
                                .background(Lime)
                        )
                    }
                }
            }

            repo.description?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            if (!userRepo) {
                FlowRow {
                    repo.topics?.forEach {
                        Text(
                            it,
                            color = Teal,
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                            modifier = Modifier
                                .padding(vertical = 4.dp)
                                .padding(end = 4.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(TealTransparent)
                                .padding(vertical = 4.dp, horizontal = 8.dp)
                        )
                    }
                }
            }

            if (userRepo) {
                Row(Modifier.padding(vertical = 4.dp)) {
                    /*      if (repo.fork == true && !repo.forksURL.isNullOrEmpty()) {
                              Text(
                                  "Forked from ${repo.forksURL}",
                                  style = MaterialTheme.typography.displaySmall,
                                  color = Grey
                              )
                              Box(Modifier.width(6.dp))
                          }
      */
                    if (!repo.updatedAt.isNullOrEmpty()) {
                        Text(
                            "Updated ${timeAgo(repo.updatedAt)}",
                            style = MaterialTheme.typography.displaySmall,
                            color = Grey
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun TextDrawable(text: String, textColor: Color = Black, drawableStart: @Composable () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        drawableStart()
        Box(Modifier.width(4.dp))
        Text(text = text, color = textColor, style = MaterialTheme.typography.displaySmall)
    }
}

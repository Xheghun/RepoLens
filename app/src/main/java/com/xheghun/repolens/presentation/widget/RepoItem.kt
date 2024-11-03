package com.xheghun.repolens.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xheghun.repolens.R
import com.xheghun.repolens.presentation.theme.DeepPurple
import com.xheghun.repolens.presentation.theme.GreyLight
import com.xheghun.repolens.presentation.theme.Lime
import com.xheghun.repolens.presentation.theme.ManRope
import com.xheghun.repolens.presentation.theme.Teal
import com.xheghun.repolens.presentation.theme.TealTransparent

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RepoItem() {
    val tags = listOf("Design-System", "Component-misc", "Status-New")

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
                Image(
                    painter = painterResource(id = R.drawable.repo_img),
                    contentDescription = "repo-image",
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                )

                Box(Modifier.width(10.dp))

                Row(Modifier.weight(1f)) {
                    Text(
                        text = "Docker-lib/",
                        color = DeepPurple,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "PythonData",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 12.sp)
                    )
                }

                TextDrawable(text = "10") {
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "github star"
                    )
                }
                Box(Modifier.width(10.dp))
                TextDrawable(text = "10") {
                    Box(
                        Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(Lime)
                    )
                }
            }

            Text(
                "These are random words that will be replaced in due time. Config files for my github profile",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            FlowRow {
                tags.forEach {
                    Text(
                        it,
                        color = Teal,
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 12.sp),
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(TealTransparent)
                            .padding(vertical = 6.dp, horizontal = 10.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun TextDrawable(text: String, drawableStart: @Composable () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        drawableStart()
        Box(Modifier.width(4.dp))
        Text(text = text, style = TextStyle(fontSize = 12.sp, fontFamily = ManRope))
    }
}

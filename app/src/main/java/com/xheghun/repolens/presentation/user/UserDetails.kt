package com.xheghun.repolens.presentation.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.xheghun.repolens.R
import com.xheghun.repolens.presentation.theme.Black
import com.xheghun.repolens.presentation.theme.GreyLight
import com.xheghun.repolens.presentation.theme.IconColor
import com.xheghun.repolens.presentation.widget.RepoItem
import com.xheghun.repolens.presentation.widget.TextDrawable
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserDetails(navController: NavController, model: UsersViewModel) {
    val uriHandler = LocalUriHandler.current
    val user = model.selectedUser.collectAsStateWithLifecycle().value
    val userRepos = model.selectedUserRepos.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        model.getUserRepository()
    }

    Column(Modifier.padding(12.dp)) {

        //NAVIGATION
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { navController.popBackStack() }) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "nav back",
                modifier = Modifier
                    .clip(CircleShape)
                    .padding(vertical = 12.dp)
            )
            Text(
                "Users",
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }

        //PROFILE DETAILS HEADER
        Column {
            Row(Modifier.padding(vertical = 6.dp)) {
                Image(
                    painter = painterResource(R.drawable.repo_img),
                    contentDescription = "profile image",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Box(Modifier.width(10.dp))
                Column {
                    user?.name?.let {
                        Text(
                            it, style = MaterialTheme.typography.titleMedium
                        )
                        Box(Modifier.height(2.dp))
                    }

                    user?.login?.let { Text(it, style = MaterialTheme.typography.bodyLarge) }
                }
            }

            user?.bio?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.displayMedium
                )
            }

            Row(Modifier.padding(vertical = 6.dp)) {
                user?.location?.let {
                    TextDrawable(text = it, textColor = IconColor) {
                        Image(
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = "location icon"
                        )
                    }

                    Box(Modifier.width(6.dp))
                }

                if (!user?.blog.isNullOrEmpty()) {
                    Box(Modifier.clickable { uriHandler.openUri(user?.blog!!) }) {
                        TextDrawable(text = user?.blog!!) {
                            Image(
                                painter = painterResource(id = R.drawable.link),
                                contentDescription = "website icon"
                            )
                        }
                    }
                }
            }

            Row(Modifier.padding(vertical = 6.dp)) {
                user?.followers?.let {
                    TextDrawable(text = "$it followers .", textColor = IconColor) {
                        Image(
                            painter = painterResource(id = R.drawable.people),
                            contentDescription = "followers icon"
                        )
                    }

                    Box(Modifier.width(6.dp))
                }

                user?.following?.let {
                    Text(
                        text = "$it following",
                        color = IconColor,
                        style = MaterialTheme.typography.displaySmall
                    )
                }
            }
        }

        //REPO COUNT
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 18.dp, bottom = 8.dp)
        ) {
            Text("Repositories", style = MaterialTheme.typography.titleSmall)
            Box(Modifier.width(3.dp))
            if (userRepos.isNotEmpty()) {
                Text(
                    "${userRepos.size}",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(GreyLight)
                        .padding(horizontal = 6.dp)
                )
            }
        }

        Row(Modifier.background(GreyLight)) {
            Box(
                Modifier
                    .weight(1.8f)
                    .height(2.dp)
                    .padding(start = 4.dp)
                    .background(Black)
            )
            Box(Modifier.weight(4f))
        }

        //REPO LIST

        LazyColumn(Modifier.weight(1f)) {
            itemsIndexed(userRepos) { index, repo ->
                RepoItem(repo)
            }
        }

        //EMPTY STATE
        if (userRepos.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.empty_user_repo),
                    contentDescription = "empty search state"
                )
                androidx.compose.material3.Text(
                    text = "This user  doesn’t have repositories yet, come back later :-)",
                    color = Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }
        }
    }
}
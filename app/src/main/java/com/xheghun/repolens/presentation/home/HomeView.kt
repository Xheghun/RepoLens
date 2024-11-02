package com.xheghun.repolens.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.xheghun.repolens.R
import com.xheghun.repolens.presentation.Routes
import com.xheghun.repolens.presentation.theme.DeepPurple
import com.xheghun.repolens.presentation.theme.PurpleTransparent
import com.xheghun.repolens.presentation.theme.Teal
import com.xheghun.repolens.presentation.theme.TealTransparent
import com.xheghun.repolens.presentation.widget.NavTile
import com.xheghun.repolens.presentation.widget.PageTitle

@Composable
fun HomeView(navController: NavHostController) {
    Column(Modifier.padding(horizontal = 12.dp)) {
        PageTitle(title = "Home")
        Row {
            NavTile(
                title = "Users",
                iconResource = R.drawable.user,
                iconTint = Teal,
                backgroundColor = TealTransparent,
                modifier = Modifier
                    .weight(1f)
            ) {
                navController.navigate(Routes.Users.name)
            }
            Box(modifier = Modifier.width(10.dp))
            NavTile(
                title = "Repositories",
                iconResource = R.drawable.paste_code,
                iconTint = DeepPurple,
                backgroundColor = PurpleTransparent,
                modifier = Modifier
                    .weight(1f)
            ) {
                navController.navigate(Routes.Repositories.name)
            }
        }
    }
}
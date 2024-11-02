package com.xheghun.repolens.presentation.user

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.xheghun.repolens.presentation.widget.PageTitle

@Composable
fun UserView(navController: NavHostController) {
    Column {
        PageTitle(title = "Users")
    }
}
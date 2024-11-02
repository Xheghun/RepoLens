package com.xheghun.repolens.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.xheghun.repolens.presentation.widget.PageTitle

@Composable
fun SearchView(navController: NavHostController) {
    Column {

        PageTitle(title = "Repositories")
    }
}
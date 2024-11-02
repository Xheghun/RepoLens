package com.xheghun.repolens.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.xheghun.repolens.presentation.theme.RepoLensTheme

@Composable
fun MainScreen(navController: NavController) {



}


@Preview
@Composable
fun MainPreview() {
    RepoLensTheme {
        MainScreen(rememberNavController())
    }
}
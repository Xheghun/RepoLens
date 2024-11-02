package com.xheghun.repolens.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.xheghun.repolens.presentation.home.HomeView
import com.xheghun.repolens.presentation.search.SearchView
import com.xheghun.repolens.presentation.theme.RepoLensTheme
import com.xheghun.repolens.presentation.user.UserView

data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: ImageVector)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val screens = listOf(
                TopLevelRoute("", Routes.Home, Icons.Default.Info),
                TopLevelRoute("", Routes.Search, Icons.Default.Search),
                TopLevelRoute("", Routes.User, Icons.Default.AccountCircle),
            )
            RepoLensTheme {
                Scaffold(
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination

                        BottomNavigation(
                            backgroundColor = Color.White,
                        ) {
                            screens.forEach { screen ->
                                BottomNavigationItem(
                                    modifier = Modifier.padding(12.dp),
                                    selected = currentDestination?.hierarchy?.any {
                                        it.hasRoute(screen.route::class)
                                    } == true,
                                    onClick = {
                                        navController.navigate(screen.route.name) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = false
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    icon = { Icon(screen.icon, contentDescription = "nav icon") },
                                    label = {
                                        Text(screen.name)
                                    })
                            }
                        }

                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Home.name,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Routes.Home.name) { HomeView() }
                        composable(Routes.Search.name) { SearchView() }
                        composable(Routes.User.name) { UserView() }
                    }
                }

            }
        }
    }
}

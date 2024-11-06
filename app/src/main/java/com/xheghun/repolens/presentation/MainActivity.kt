package com.xheghun.repolens.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.xheghun.repolens.R
import com.xheghun.repolens.presentation.helpers.Routes
import com.xheghun.repolens.presentation.home.HomeView
import com.xheghun.repolens.presentation.search.SearchView
import com.xheghun.repolens.presentation.theme.RepoLensTheme
import com.xheghun.repolens.presentation.user.UserDetails
import com.xheghun.repolens.presentation.user.UserView
import com.xheghun.repolens.presentation.user.UsersViewModel
import org.koin.androidx.compose.navigation.koinNavViewModel

data class TopLevelRoute<T : Any>(
    val name: String,
    val route: T,
    val icon: Int,
    val selectedIcon: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val screens = listOf(
                TopLevelRoute("Home", Routes.Home, R.drawable.home, R.drawable.home_active),
                TopLevelRoute("Repositories", Routes.Repositories, R.drawable.search, R.drawable.search_active),
                TopLevelRoute("Users", Routes.Users, R.drawable.user, R.drawable.user_active),
            )
            RepoLensTheme {
                Scaffold(
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination

                        NavigationBar(
                            containerColor = Color.White,
                            tonalElevation = 12.dp,
                        ) {
                            screens.forEach { screen ->
                                val isSelected = currentDestination?.hierarchy?.any {
                                    it.hasRoute(screen.route.name, null)
                                } == true
                                NavigationBarItem(
                                    selected = isSelected,
                                    onClick = {
                                        navController.navigate(screen.route.name) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = false
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    icon = {
                                        if (!isSelected)
                                            Icon(
                                                painter = painterResource(screen.icon),
                                                contentDescription = "${screen.name} nav icon"
                                            )
                                        else
                                            Icon(
                                                painter = painterResource(id = screen.selectedIcon),
                                                contentDescription = "${screen.name} active nav icon"
                                            )
                                    },
                                    label = {
                                        Text(
                                            screen.name,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            style = MaterialTheme.typography.titleSmall,
                                        )
                                    },
                                    colors = NavigationBarItemDefaults.colors(indicatorColor = Color.White)
                                )
                            }
                        }

                    }
                ) { innerPadding ->
                    val userViewModel = koinNavViewModel<UsersViewModel>()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Home.name,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Routes.Home.name) { HomeView(navController) }
                        composable(Routes.Repositories.name) { SearchView(navController) }
                        composable(Routes.Users.name) { UserView(navController, userViewModel) }
                        composable(Routes.UserDetails.name) { UserDetails(navController, userViewModel) }
                    }
                }

            }
        }
    }
}

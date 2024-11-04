package com.xheghun.repolens.presentation.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.xheghun.repolens.presentation.Routes
import com.xheghun.repolens.presentation.search.SearchViewModel
import com.xheghun.repolens.presentation.widget.PageTitle
import com.xheghun.repolens.presentation.widget.SearchBar
import com.xheghun.repolens.presentation.widget.UserItem

@Composable
fun UserView(navController: NavHostController) {
    val model = viewModel<SearchViewModel>()

    Column(Modifier.padding(horizontal = 12.dp)) {
        PageTitle(title = "Users")

        Column {

            //SEARCH BAR
            SearchBar(
                hint = "Search for users...",
                value = model.searchValue.collectAsStateWithLifecycle().value,
                onValueChange = { newValue -> model.updateSearch(newValue) },
                onSearchPressed = {}
            )

            LazyColumn(Modifier.weight(1f)) {
                items(12) {
                    UserItem { navController.navigate(Routes.UserDetails.name) }
                }
            }
        }
    }
}
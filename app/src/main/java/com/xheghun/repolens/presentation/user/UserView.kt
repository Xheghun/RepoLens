package com.xheghun.repolens.presentation.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.xheghun.repolens.R
import com.xheghun.repolens.presentation.Routes
import com.xheghun.repolens.presentation.search.SearchViewModel
import com.xheghun.repolens.presentation.theme.Black
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
                onValueChange = { newValue -> model.updateSearchQuery(newValue) },
                onSearchPressed = {}
            )


            //USER LIST
            if(false) {
                LazyColumn(Modifier.weight(1f)) {
                    items(12) {
                        UserItem { navController.navigate(Routes.UserDetails.name) }
                    }
                }
            }

            //EMPTY STATE
            if(true) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.search_prompt),
                        contentDescription = "empty search state"
                    )
                    androidx.compose.material3.Text(
                        text = "We’ve searched the ends of the earth and we’ve not found this user, please try again",
                        color = Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
            }
        }
    }
}
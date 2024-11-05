package com.xheghun.repolens.presentation.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.xheghun.repolens.R
import com.xheghun.repolens.presentation.Routes
import com.xheghun.repolens.presentation.ScreenState
import com.xheghun.repolens.presentation.theme.Black
import com.xheghun.repolens.presentation.widget.PageTitle
import com.xheghun.repolens.presentation.widget.SearchBar
import com.xheghun.repolens.presentation.widget.UserItem
import com.xheghun.repolens.presentation.widget.screenWidthPercentage

@Composable
fun UserView(navController: NavHostController, model: UsersViewModel) {

    val screenState = model.screenState.collectAsStateWithLifecycle().value
    val users = model.userList.collectAsStateWithLifecycle().value

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(Modifier.padding(horizontal = 12.dp)) {
        PageTitle(title = "Users")

        Column {

            //SEARCH BAR
            SearchBar(
                hint = "Search for users...",
                value = model.searchValue.collectAsStateWithLifecycle().value,
                onValueChange = { newValue -> model.updateSearchQuery(newValue) },
                onSearchPressed = {
                    keyboardController?.hide()
                    model.searchUser()
                }
            )

            Box(Modifier.height(6.dp))

            //USER LIST
            if (users.isNotEmpty()) {
                LazyColumn(Modifier.weight(1f)) {
                    itemsIndexed(users) { index, user ->
                        UserItem(user) {
                            if (model.selectedUserIndex.value != index) {
                                model.clearRepos()
                            }
                            model.updateSelectedUserIndex(index)
                            navController.navigate(Routes.UserDetails.name)
                        }
                    }
                }
            }


            //EMPTY STATE
            if (screenState == ScreenState.Default || (screenState == ScreenState.Result && users.isEmpty())) {
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
                    Text(
                        text = model.emptySearchStateText.collectAsStateWithLifecycle().value,
                        color = Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(
                            vertical = 10.dp,
                            horizontal = screenWidthPercentage(0.15f)
                        )
                    )
                }
            }
        }
    }
}
package com.xheghun.repolens.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.xheghun.repolens.R
import com.xheghun.repolens.presentation.ScreenState
import com.xheghun.repolens.presentation.theme.EmptyState
import com.xheghun.repolens.presentation.widget.PageTitle
import com.xheghun.repolens.presentation.widget.ProgressIndicator
import com.xheghun.repolens.presentation.widget.RepoItem
import com.xheghun.repolens.presentation.widget.SearchBar
import com.xheghun.repolens.presentation.widget.screenWidthPercentage
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchView(navController: NavHostController) {

    val model = koinViewModel<SearchViewModel>()
    val repos = model.repoList.collectAsStateWithLifecycle().value
    val screenState = model.screenState.collectAsStateWithLifecycle().value

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        //HEADER
        PageTitle(title = "Repositories")

        //SEARCH BAR
        SearchBar(
            hint = "Search for repositories...",
            value = model.searchValue.collectAsStateWithLifecycle().value,
            onValueChange = { newValue -> model.updateSearchQuery(newValue) },
            onSearchPressed = {
                keyboardController?.hide()
                model.searchRepo()
            }
        )

        Box(Modifier.height(6.dp))

        //RESULT LIST
        if (repos.isNotEmpty())
            LazyColumn(Modifier.weight(1f)) {
                items(repos.size) { index ->
                    RepoItem(repos[index])
                }
            }


        //LOADING
        if (screenState == ScreenState.Loading) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                ProgressIndicator()
            }
        }

        if (screenState == ScreenState.Default || (screenState == ScreenState.Result && repos.isEmpty()))
        //EMPTY STATE
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
                    text = model.emptyStateText.collectAsStateWithLifecycle().value,
                    color = EmptyState,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(
                        vertical = 10.dp,
                        horizontal = screenWidthPercentage(0.15f)
                    )
                )
            }

    }
}
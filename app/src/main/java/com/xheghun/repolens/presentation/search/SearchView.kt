package com.xheghun.repolens.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.xheghun.repolens.R
import com.xheghun.repolens.presentation.theme.Black
import com.xheghun.repolens.presentation.theme.EmptyState
import com.xheghun.repolens.presentation.theme.Grey
import com.xheghun.repolens.presentation.widget.PageTitle
import com.xheghun.repolens.presentation.widget.RepoItem
import com.xheghun.repolens.presentation.widget.SearchBar
import com.xheghun.repolens.presentation.widget.SearchTextField

@Composable
fun SearchView(navController: NavHostController) {

    val model = viewModel<SearchViewModel>()

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
            onValueChange = { newValue -> model.updateSearch(newValue) },
            onSearchPressed = {}
        )

        //RESULT LIST
        LazyColumn(Modifier.weight(1f)) {
            items(10) {
                RepoItem()
            }
        }

        if (false)
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
                    text = "Search Github repositories, issues and pull request!",
                    color = EmptyState,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }

    }
}
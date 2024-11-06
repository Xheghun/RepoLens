package com.xheghun.repolens.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xheghun.repolens.data.models.Repo
import com.xheghun.repolens.domain.GithubServiceRepo
import com.xheghun.repolens.presentation.helpers.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val apiRepo: GithubServiceRepo) : ViewModel() {
    private val _searchValue = MutableStateFlow("")
    val searchValue = _searchValue.asStateFlow()

    private val _screenState = MutableStateFlow(ScreenState.Default)
    val screenState = _screenState.asStateFlow()

    private val _emptyStateText =
        MutableStateFlow("Search Github repositories, issues and pull request!")
    val emptyStateText = _emptyStateText.asStateFlow()

    private val _repoList = MutableStateFlow(listOf<Repo>())
    val repoList = _repoList.asStateFlow()

    fun updateSearchQuery(value: String) {
        _searchValue.value = value
    }

    fun searchRepo() {
        viewModelScope.launch {
            _screenState.value = ScreenState.Loading
            apiRepo.searchRepository(_searchValue.value)
                .onSuccess {
                    _screenState.value = ScreenState.Result
                    _repoList.value = it

                    if (it.isEmpty()) _emptyStateText.value =
                        "We’ve searched the ends of the earth, repository not found, please try again"
                }
                .onFailure {
                    _screenState.value = ScreenState.Result
                }
        }
    }
}
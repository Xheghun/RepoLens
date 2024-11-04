package com.xheghun.repolens.presentation.user

import androidx.lifecycle.ViewModel
import com.xheghun.repolens.domain.GithubServiceRepo
import com.xheghun.repolens.presentation.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UsersViewModel(private val apiRepo: GithubServiceRepo) : ViewModel() {
    private val _searchValue = MutableStateFlow("")
    val searchValue = _searchValue.asStateFlow()

    private val _screenState = MutableStateFlow(ScreenState.Default)
    val screenState = _screenState.asStateFlow()

    private val _emptyStateText =
        MutableStateFlow("Search for  Github users...")
    val emptyStateText = _emptyStateText.asStateFlow()

    private val _userList = MutableStateFlow(listOf(""))
    val userList = _userList.asStateFlow()

    fun updateSearchQuery(value: String) {
        _searchValue.value = value
    }
}
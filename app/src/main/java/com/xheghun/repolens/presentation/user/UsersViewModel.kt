package com.xheghun.repolens.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xheghun.repolens.data.models.Repo
import com.xheghun.repolens.data.models.User
import com.xheghun.repolens.domain.GithubServiceRepo
import com.xheghun.repolens.presentation.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsersViewModel(private val apiRepo: GithubServiceRepo) : ViewModel() {
    private val _searchValue = MutableStateFlow("")
    val searchValue = _searchValue.asStateFlow()

    private val _screenState = MutableStateFlow(ScreenState.Default)
    val screenState = _screenState.asStateFlow()

    private val _emptyStateText = MutableStateFlow("Search for  Github users...")
    val emptyStateText = _emptyStateText.asStateFlow()

    private val _userList = MutableStateFlow(listOf<User>())
    val userList = _userList.asStateFlow()

    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser = _selectedUser.asStateFlow()

    private val _selectedUserRepos = MutableStateFlow(listOf<Repo>())
    val selectedUserRepos = _selectedUserRepos.asStateFlow()

    fun updateSearchQuery(value: String) {
        _searchValue.value = value
    }

    fun updateSelectedUser(user: User) {
        _selectedUser.value = user
    }

    fun searchUser() {
        viewModelScope.launch {
            _screenState.value = ScreenState.Loading

            apiRepo.searchUsers(_searchValue.value).onSuccess {
                _screenState.value = ScreenState.Result
                _userList.value = it

                if (it.isEmpty()) _emptyStateText.value =
                    "We’ve searched the ends of the earth, repository not found, please try again"
            }.onFailure {
                _screenState.value = ScreenState.Result
            }
        }
    }

    fun getUserRepository() {
        viewModelScope.launch {
            apiRepo.fetchUserRepos(selectedUser.value?.login ?: searchValue.value).onSuccess {
                _selectedUserRepos.value = it
            }
        }
    }
}
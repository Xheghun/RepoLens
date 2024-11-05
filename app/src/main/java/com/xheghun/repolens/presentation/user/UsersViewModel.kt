package com.xheghun.repolens.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xheghun.repolens.data.models.Repo
import com.xheghun.repolens.data.models.User
import com.xheghun.repolens.domain.GithubServiceRepo
import com.xheghun.repolens.presentation.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class UsersViewModel(private val apiRepo: GithubServiceRepo) : ViewModel() {
    private val _searchValue = MutableStateFlow("")
    val searchValue = _searchValue.asStateFlow()

    private val _screenState = MutableStateFlow(ScreenState.Default)
    val screenState = _screenState.asStateFlow()

    private val _emptySearchStateText = MutableStateFlow("Search for  Github users...")
    val emptySearchStateText = _emptySearchStateText.asStateFlow()

    private val _userList = MutableStateFlow(listOf<User>())
    val userList = _userList.asStateFlow()

    private val _selectedUserIndex = MutableStateFlow<Int?>(null)
    val selectedUserIndex = _selectedUserIndex.asStateFlow()

    private val _selectedUserRepos = MutableStateFlow(listOf<Repo>())
    val selectedUserRepos = _selectedUserRepos.asStateFlow()

    fun updateSearchQuery(value: String) {
        _searchValue.value = value
    }

    fun updateSelectedUserIndex(userIndex: Int) {
        _selectedUserIndex.value = userIndex
    }

    fun searchUser() {
        viewModelScope.launch {
            _screenState.value = ScreenState.Loading

            apiRepo.searchUsers(_searchValue.value)
                .catch { _screenState.value = ScreenState.Result }
                .collect {
                    _screenState.value = ScreenState.Result
                    _userList.value = it

                    if (it.isEmpty()) _emptySearchStateText.value =
                        "We’ve searched the ends of the earth, repository not found, please try again"
                }

        }
    }

    fun getUserRepository() {
        //check if we already have the repo list
        if (_selectedUserRepos.value.isNotEmpty()) return

        _selectedUserIndex.value?.let { index ->
            _screenState.value = ScreenState.Loading
            viewModelScope.launch {
                apiRepo.fetchUserRepos(
                    _userList.value[index].login ?: searchValue.value
                ).onSuccess {
                    _screenState.value = ScreenState.Result
                    _selectedUserRepos.value = it
                }
                    .onFailure { _screenState.value = ScreenState.Result }
            }
        }
    }

    fun clearRepos() {
        _selectedUserRepos.value = listOf()
    }
}
package com.xheghun.repolens.presentation.search

import androidx.lifecycle.ViewModel
import com.xheghun.repolens.data.api.GithubApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchViewModel(apiService: GithubApiService) : ViewModel() {
    private val _searchValue = MutableStateFlow("")
    val searchValue = _searchValue.asStateFlow()

    fun updateSearch(value: String) {
        _searchValue.value = value
    }
}
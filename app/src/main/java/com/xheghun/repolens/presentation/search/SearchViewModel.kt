package com.xheghun.repolens.presentation.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchViewModel : ViewModel() {
    private val _searchValue = MutableStateFlow("")
    val searchValue = _searchValue.asStateFlow()

    fun updateSearch(value: String) {
        _searchValue.value = value
    }
}
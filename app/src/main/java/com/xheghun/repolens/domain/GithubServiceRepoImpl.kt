package com.xheghun.repolens.domain

import com.xheghun.repolens.data.api.GithubApiService
import com.xheghun.repolens.data.models.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubServiceRepoImpl(private val apiService: GithubApiService) : GithubServiceRepo {
    override suspend fun searchRepository(value: String): List<Item> = withContext(Dispatchers.IO) {
        apiService.searchRepository(value).items
    }
}
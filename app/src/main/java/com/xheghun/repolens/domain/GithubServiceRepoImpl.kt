package com.xheghun.repolens.domain

import com.xheghun.repolens.data.api.GithubApiService
import com.xheghun.repolens.data.models.Item

class GithubServiceRepoImpl(private val apiService: GithubApiService) : GithubServiceRepo {
    override suspend fun searchRepository(value: String): Result<List<Item>> = runCatching {
        apiService.searchRepository(value).items
    }
}
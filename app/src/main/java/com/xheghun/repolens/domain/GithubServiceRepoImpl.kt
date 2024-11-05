package com.xheghun.repolens.domain

import com.xheghun.repolens.data.api.GithubApiService
import com.xheghun.repolens.data.models.Repo
import com.xheghun.repolens.data.models.User

class GithubServiceRepoImpl(private val apiService: GithubApiService) : GithubServiceRepo {
    override suspend fun searchRepository(value: String): Result<List<Repo>> = runCatching {
        apiService.searchRepository(value).items
    }

    override suspend fun searchUsers(value: String): Result<List<User>> = runCatching {
        apiService.searchUser(value).items.map { user -> fetchUser(user.login!!) }
    }

    override suspend fun fetchUser(user: String): User =
        apiService.fetchUserInfo(user)

    override suspend fun fetchUserRepos(user: String): Result<List<Repo>> = runCatching {
        apiService.fetchUserRepo(user)
    }
}
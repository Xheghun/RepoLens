package com.xheghun.repolens.domain

import com.xheghun.repolens.data.api.GithubApiService
import com.xheghun.repolens.data.models.Repo
import com.xheghun.repolens.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class GithubServiceRepoImpl(private val apiService: GithubApiService) : GithubServiceRepo {
    override suspend fun searchRepository(value: String): Result<List<Repo>> = runCatching {
        apiService.searchRepository(value).items
    }

    override suspend fun searchUsers(value: String): Flow<List<User>> = flow {
        val matchedUsers = apiService.searchUser(value).items
        emit(matchedUsers)

        val matchedUsersProfile =
            withContext(Dispatchers.IO) {
                matchedUsers.map { user -> async { fetchUser(user.login!!) } }.awaitAll()
            }

        emit(matchedUsersProfile)

    }

    override suspend fun fetchUser(user: String): User =
        apiService.fetchUserInfo(user)

    override suspend fun fetchUserRepos(user: String): Result<List<Repo>> = runCatching {
        apiService.fetchUserRepo(user)
    }
}
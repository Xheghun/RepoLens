package com.xheghun.repolens.domain

import com.xheghun.repolens.data.models.Repo
import com.xheghun.repolens.data.models.User
import kotlinx.coroutines.flow.Flow

interface GithubServiceRepo {
    suspend fun searchRepository(value: String): Result<List<Repo>>
    suspend fun searchUsers(value: String): Flow<List<User>>
    suspend fun fetchUser(user: String): User
    suspend fun fetchUserRepos(user: String): Result<List<Repo>>
}
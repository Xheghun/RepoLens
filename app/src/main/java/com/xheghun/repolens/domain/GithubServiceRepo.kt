package com.xheghun.repolens.domain

import com.xheghun.repolens.data.models.Repo
import com.xheghun.repolens.data.models.User

interface GithubServiceRepo {
    suspend fun searchRepository(value: String): Result<List<Repo>>
    suspend fun searchUsers(value: String): Result<List<User>>
}
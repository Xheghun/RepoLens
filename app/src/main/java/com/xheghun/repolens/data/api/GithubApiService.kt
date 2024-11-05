package com.xheghun.repolens.data.api

import com.xheghun.repolens.data.models.Repo
import com.xheghun.repolens.data.models.RepoSearchResult
import com.xheghun.repolens.data.models.User
import com.xheghun.repolens.data.models.UserSearchResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {
    @GET("search/repositories")
    suspend fun searchRepository(
        @Query("q") value: String
    ): RepoSearchResult


    @GET("search/users")
    suspend fun searchUser(@Query("q") value: String): UserSearchResult

    @GET("users/{userName}")
    suspend fun fetchUserInfo(@Path("userName") userName: String): User

    @GET("users/{userName}/repos")
    suspend fun fetchUserRepo(@Path("userName") userName: String): List<Repo>
}
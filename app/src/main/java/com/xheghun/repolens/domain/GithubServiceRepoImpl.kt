package com.xheghun.repolens.domain

import android.util.Log
import com.xheghun.repolens.data.api.GithubApiService
import com.xheghun.repolens.data.database.GithubRepoDatabase
import com.xheghun.repolens.data.entities.toUser
import com.xheghun.repolens.data.entities.toUserEntity
import com.xheghun.repolens.data.models.Repo
import com.xheghun.repolens.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class GithubServiceRepoImpl(
    private val apiService: GithubApiService,
    private val githubRepoDatabase: GithubRepoDatabase
) : GithubServiceRepo {
    override suspend fun searchRepository(value: String): Result<List<Repo>> =
        withContext(Dispatchers.IO) {
            runCatching {
                apiService.searchRepository(value).items
            }
        }

    /*
    * The Github /search/users API doesn't return the complete user details, so we have to make another request
    * to users/{userName} to get it.
    *
    * In order to mitigate this, the flow api would be used to initially emit the partial data
    * and then emit the complete details once it's available. This significantly reduces the amount of time take
    * to return the search result
    * */
    override suspend fun searchUsers(value: String): Flow<List<User>> =
        withContext(Dispatchers.IO) {
            val userDao = githubRepoDatabase.usersDao()

            //CHECK if there's a match in the local database
            val cachedUsers = userDao.getAllUsers().filter {
                val searchQuery = value.trim().lowercase()

                it.login.lowercase().contains(searchQuery) || it.name?.lowercase()
                    ?.contains(searchQuery) == true
            }

            flow {
                if (cachedUsers.isNotEmpty()) {
                    emit(cachedUsers.map { it.toUser() })
                } else {
                    val matchedUsers = apiService.searchUser(value).items
                    emit(matchedUsers)

                    val matchedUsersProfile =
                        withContext(Dispatchers.IO) {
                            matchedUsers.map { user -> async { fetchUser(user.login!!) } }
                                .awaitAll()
                        }

                    emit(matchedUsersProfile)

                    userDao
                        .insertUsers(matchedUsersProfile.map {
                            it.toUserEntity()
                        })
                }
            }
        }

    override suspend fun fetchUser(user: String): User =
        apiService.fetchUserInfo(user)

    override suspend fun fetchUserRepos(user: String): Result<List<Repo>> =
        withContext(Dispatchers.IO) {
            runCatching {
                apiService.fetchUserRepo(user)
            }
        }
}
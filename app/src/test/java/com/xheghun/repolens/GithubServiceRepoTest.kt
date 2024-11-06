package com.xheghun.repolens

import com.xheghun.repolens.data.api.GithubApiService
import com.xheghun.repolens.data.models.Repo
import com.xheghun.repolens.data.models.RepoSearchResult
import com.xheghun.repolens.data.models.User
import com.xheghun.repolens.data.models.UserSearchResult
import com.xheghun.repolens.domain.GithubServiceRepo
import com.xheghun.repolens.domain.GithubServiceRepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class GithubServiceRepoTest {

    @Mock
    private lateinit var apiService: GithubApiService
    private lateinit var githubServiceRepo: GithubServiceRepo
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        githubServiceRepo = GithubServiceRepoImpl(apiService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchRepository returns successful result when API call succeeds`() = runTest {
        // Given
        val searchQuery = "kotlin"
        val sampleRepos = listOf(
            Repo(id = 1, name = "repo1"),
            Repo(id = 2, name = "repo2")
        )
        val sampleResponse = RepoSearchResult(sampleRepos)

        `when`(apiService.searchRepository(searchQuery)).thenReturn(sampleResponse)

        // When
        val result = githubServiceRepo.searchRepository(searchQuery)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(sampleRepos, result.getOrNull())
    }

    @Test
    fun `searchUsers emits both initial users and their detailed profiles`() = runTest {
        // Given
        val searchQuery = "john"
        val initialUsers = listOf(
            User(id = 1, login = "john1"),
            User(id = 2, login = "john2")
        )
        val detailedUsers = listOf(
            User(id = 1, login = "john1", name = "John One"),
            User(id = 2, login = "john2", name = "John Two")
        )

        `when`(apiService.searchUser(searchQuery))
            .thenReturn(UserSearchResult(items = initialUsers))

        `when`(apiService.fetchUserInfo("john1")).thenReturn(detailedUsers[0])
        `when`(apiService.fetchUserInfo("john2")).thenReturn(detailedUsers[1])

        // When
        val emissions = githubServiceRepo.searchUsers(searchQuery).toList()

        // Then
        assertEquals(2, emissions.size)
        assertEquals(initialUsers, emissions[0])
        assertEquals(detailedUsers, emissions[1])
    }

    @Test
    fun `fetchUserRepos returns successful result when API call succeeds`() = runTest {
        // Given
        val username = "johnDoe"
        val mockRepos = listOf(
            Repo(id = 1, name = "repo1"),
            Repo(id = 2, name = "repo2")
        )

        `when`(apiService.fetchUserRepo(username)).thenReturn(mockRepos)

        // When
        val result = githubServiceRepo.fetchUserRepos(username)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(mockRepos, result.getOrNull())
    }

    @Test
    fun `fetchUser returns user info when API call succeeds`() = runTest {
        // Given
        val username = "johnDoe"
        val mockUser = User(id = 1, login = username, name = "John Doe")

        `when`(apiService.fetchUserInfo(username)).thenReturn(mockUser)

        // When
        val result = githubServiceRepo.fetchUser(username)

        // Then
        assertEquals(mockUser, result)
    }

    @Test
    fun `searchRepository returns failure when API throws exception`() = runTest {
        // Given
        val searchQuery = "kotlin"
        `when`(apiService.searchRepository(searchQuery)).thenThrow(RuntimeException("API Error"))

        // When
        val result = githubServiceRepo.searchRepository(searchQuery)

        // Then
        assertTrue(result.isFailure)
    }

    @Test
    fun `searchUsers emits empty lists when no users found`() = runTest {
        // Given
        val searchQuery = "nonexistent"
        `when`(apiService.searchUser(searchQuery))
            .thenReturn(UserSearchResult(items = emptyList()))

        // When
        val emissions = githubServiceRepo.searchUsers(searchQuery).toList()

        // Then
        assertEquals(2, emissions.size)
        assertTrue(emissions[0].isEmpty())
        assertTrue(emissions[1].isEmpty())
    }
}
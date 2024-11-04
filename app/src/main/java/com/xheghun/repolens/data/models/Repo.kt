package com.xheghun.repolens.data.models

data class Repo(
    val id: Long? = 0,
    val name: String? = "",
    val fullName: String? = "",
    val private: Boolean? = false,
    val owner: User? = null,
    val htmlURL: String? = "",
    val description: String? = "",
    val fork: Boolean? = false,
    val url: String? = "",
    val forksURL: String? = "",
    val deploymentsURL: String? = "",
    val createdAt: String? = "",
    val updatedAt: String? = "",
    val stargazersCount: Long? = 0,
    val language: String? = "",
    val topics: List<String>? = listOf(),
    val visibility: String? = "",
    val forks: Long? = 0,
    val openIssues: Long? = 0,
    val watchers: Long? = 0,
    val defaultBranch: String? = "",
    val score: Long? = 0
)
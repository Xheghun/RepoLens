package com.xheghun.repolens.data.models

data class Item(
    val id: Long,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val owner: Owner,
    val htmlURL: String,
    val description: String,
    val fork: Boolean,
    val url: String,
    val forksURL: String,
    val deploymentsURL: String,
    val createdAt: String,
    val updatedAt: String,
    val stargazersCount: Long,
    val language: String,
    val topics: List<String>,
    val visibility: String,
    val forks: Long,
    val openIssues: Long,
    val watchers: Long,
    val defaultBranch: String,
    val score: Long
)
package com.xheghun.repolens.data.models

import com.google.gson.annotations.SerializedName

data class Repo(
    val id: Long? = 0,
    val name: String? = "",
    val private: Boolean? = false,
    val owner: User? = null,
    val htmlURL: String? = "",
    val description: String? = "",
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("stargazers_count")
    val stargazersCount: Long? = 0,
    val language: String? = "",
    val topics: List<String>? = listOf()
)
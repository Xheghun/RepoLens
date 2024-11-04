package com.xheghun.repolens.domain

import com.xheghun.repolens.data.models.Item

interface GithubServiceRepo {
    suspend fun searchRepository(value: String): List<Item>
}
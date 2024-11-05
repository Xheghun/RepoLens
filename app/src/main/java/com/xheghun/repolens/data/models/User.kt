package com.xheghun.repolens.data.models

data class User(
    val login: String?,
    val id: Long?,
    val avatarURL: String?,
    val htmlURL: String?,
    val type: String?,
    val userViewType: String?,
    val location: String?,
    val followers: Long?,
    val following: Long?,
    val name: String?,
    val blog: String?,
    val email: String?,
    val bio: String?
)
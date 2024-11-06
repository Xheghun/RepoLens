package com.xheghun.repolens.data.models

import com.google.gson.annotations.SerializedName

open class User(
    val login: String? = "",
    val id: Long? = 0,
    @SerializedName("avatar_url")
    val avatarURL: String? = "URL is EMPTY",
    val location: String? = "",
    val followers: Long? = 0,
    val following: Long? = 0,
    val name: String? = "",
    val blog: String? = "",
    val email: String? = "",
    val bio: String? = ""
)
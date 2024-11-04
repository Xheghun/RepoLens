package com.xheghun.repolens.data.models

data class User(
    val login: String? = "",
    val id: Long? = 0,
    val avatarURL: String? = "",
    val htmlURL: String? = "",
    val type: String? = "",
    val userViewType: String? = ""
)
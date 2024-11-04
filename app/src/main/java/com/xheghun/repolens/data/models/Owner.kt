package com.xheghun.repolens.data.models

data class Owner(
    val login: String,
    val id: Long,
    val avatarURL: String,
    val htmlURL: String,
    val type: String,
    val userViewType: String
)
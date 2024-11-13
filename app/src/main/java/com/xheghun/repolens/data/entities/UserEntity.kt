package com.xheghun.repolens.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xheghun.repolens.data.models.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: Long = 0,
    val login: String,
    val avatarURL: String?,
    val location: String?,
    val followers: Long?,
    val following: Long?,
    val name: String?,
    val blog: String?,
    val email: String?,
    val bio: String?
)

fun UserEntity.toUser(): User {
    return User(
        this.login,
        this.id,
        this.avatarURL,
        this.location,
        this.followers,
        this.following,
        this.name,
        this.blog,
        this.email,
        this.bio
    )
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        this.id!!,
        this.login!!,
        this.avatarURL,
        this.location,
        this.followers,
        this.following,
        this.name,
        this.blog,
        this.email,
        this.bio
    )
}

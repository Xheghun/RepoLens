package com.xheghun.repolens.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xheghun.repolens.data.dao.UserDao
import com.xheghun.repolens.data.entities.UserEntity

@Database(version = 2, entities = [UserEntity::class])
abstract class GithubRepoDatabase : RoomDatabase() {
    abstract fun usersDao(): UserDao
}
package com.xheghun.repolens.di

import android.content.Context
import androidx.room.Room
import com.xheghun.repolens.data.database.GithubRepoDatabase
import org.koin.dsl.module

fun databaseModule(context: Context) = module {
    single {
        Room.databaseBuilder(
            context,
            name = "github_db",
            klass = GithubRepoDatabase::class.java,
        ).build()
    }
}
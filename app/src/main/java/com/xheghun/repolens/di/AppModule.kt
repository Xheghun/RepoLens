package com.xheghun.repolens.di

import com.xheghun.repolens.domain.GithubServiceRepo
import com.xheghun.repolens.domain.GithubServiceRepoImpl
import org.koin.dsl.module

fun appModule() = module {
    single { GithubServiceRepoImpl(get(), get()) as GithubServiceRepo } // repository
}
package com.xheghun

import android.app.Application
import com.xheghun.repolens.di.appModule
import com.xheghun.repolens.di.networkingModule
import com.xheghun.repolens.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RepoLensApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RepoLensApp)
            modules(listOf(appModule(), networkingModule(), viewModelModule()))
        }
    }
}
package com.mobiquity.assignment

import android.app.Application
import com.mobiquity.assignment.di.appModule
import com.mobiquity.assignment.di.networkModule
import com.mobiquity.assignment.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(appModule, networkModule, repositoryModule))
        }
    }
}
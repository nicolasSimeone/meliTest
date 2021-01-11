package com.example.testmeli

import android.app.Application
import com.example.testmeli.di.apiModule
import com.example.testmeli.di.productModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(apiModule, productModule))
        }
    }
}
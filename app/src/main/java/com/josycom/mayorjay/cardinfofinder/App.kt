package com.josycom.mayorjay.cardinfofinder

import android.app.Application
import com.josycom.mayorjay.cardinfofinder.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}
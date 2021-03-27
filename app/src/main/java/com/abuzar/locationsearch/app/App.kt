package com.abuzar.locationsearch.app

import android.app.Application
import org.koin.android.ext.android.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, listOf())
    }


}
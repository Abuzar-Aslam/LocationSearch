package com.abuzar.locationsearch.base

import android.app.Application
import com.abuzar.locationsearch.injection.dataModule
import com.abuzar.locationsearch.injection.viewModelModule
import org.koin.android.ext.android.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, listOf(viewModelModule, dataModule))
    }


}
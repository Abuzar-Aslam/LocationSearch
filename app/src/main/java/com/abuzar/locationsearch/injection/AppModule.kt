package com.abuzar.locationsearch.injection

import com.abuzar.locationsearch.ui.SearchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module


val viewModelModule = module{


    single { SearchViewModel(androidApplication()) }

}
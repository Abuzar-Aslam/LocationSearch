package com.abuzar.locationsearch.injection

import android.content.res.AssetManager
import com.abuzar.locationsearch.data.CityModel
import com.abuzar.locationsearch.data.Coordinates
import com.abuzar.locationsearch.ui.SearchViewModel
import com.abuzar.locationsearch.utils.CITIES_ASSETS_JSON
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader


val viewModelModule = module {


    single { SearchViewModel(androidApplication(),get()) }

}


val dataModule = module {

    single {


        val flashLightObj: ArrayList<CityModel> = try {
            val reader: Reader = InputStreamReader(get())
            val arrayListFlashType = object : TypeToken<ArrayList<CityModel>>() {}.type
            Gson().fromJson(reader, arrayListFlashType)
        } catch (e: IOException) {
            e.printStackTrace()
            val arrayList = ArrayList<CityModel>()
            arrayList.add(CityModel("", "", Coordinates(0.0, 0.0)))
            arrayList
        }
        flashLightObj
    }


    single {
        val assetManager: AssetManager = androidApplication().assets
        assetManager.open(CITIES_ASSETS_JSON)

    }

}
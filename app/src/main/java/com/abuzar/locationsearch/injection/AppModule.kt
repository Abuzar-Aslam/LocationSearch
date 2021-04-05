package com.abuzar.locationsearch.injection

import android.content.res.AssetManager
import com.abuzar.locationsearch.data.CityModel
import com.abuzar.locationsearch.data.Coordinates
import com.abuzar.locationsearch.interactors.SearchUseCase
import com.abuzar.locationsearch.ui.search.SearchCityViewModel
import com.abuzar.locationsearch.utils.CITIES_ASSETS
import com.abuzar.locationsearch.utils.CITIES_ASSETS_JSON
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader


val viewModelModule = module {

    single { SearchCityViewModel(get(),get()) }

}


val dataModule = module {


    single {
        SearchUseCase()
    }

    single {
        val citiesList: ArrayList<CityModel> = try {
            val reader: Reader = InputStreamReader(get(CITIES_ASSETS))
            val arrayListCitiesType = object : TypeToken<ArrayList<CityModel>>() {}.type
            Gson().fromJson(reader, arrayListCitiesType)
        } catch (e: IOException) {
            e.printStackTrace()
            val arrayList = ArrayList<CityModel>()
            arrayList.add(CityModel(0,"", "", Coordinates(0.0, 0.0)))
            arrayList
        }
        citiesList
    }


    single(CITIES_ASSETS) {
        val assetManager: AssetManager = androidApplication().assets
        assetManager.open(CITIES_ASSETS_JSON)
    }

}
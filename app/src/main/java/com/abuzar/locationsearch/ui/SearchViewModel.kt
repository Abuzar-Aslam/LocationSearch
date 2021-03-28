package com.abuzar.locationsearch.ui

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abuzar.locationsearch.data.CityModel
import java.security.AccessControlContext

class SearchViewModel(context: Context,
                      private val flashLightReadouts: ArrayList<CityModel>) : ViewModel() {


    private val searchedItemsResult  = ObservableArrayList<CityModel>()

    fun searchCities(searchPrefix:  String): LiveData<List<CityModel>>{

        searchedItemsResult.clear()
        for(city in flashLightReadouts){

            if(city.cityName.startsWith(searchPrefix,true))
                searchedItemsResult.add(city)

        }
        val mutableLiveData= MutableLiveData<List<CityModel>>()
        mutableLiveData.value=searchedItemsResult
        return  mutableLiveData
    }

    fun getAllCities(): LiveData<List<CityModel>> {
        val mutableLiveData= MutableLiveData<List<CityModel>>()
        mutableLiveData.value=flashLightReadouts
        return  mutableLiveData
    }


}
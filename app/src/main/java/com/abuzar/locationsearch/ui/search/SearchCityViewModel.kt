package com.abuzar.locationsearch.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abuzar.locationsearch.data.CityModel
import com.abuzar.locationsearch.interactors.SearchUseCase
import com.abuzar.locationsearch.interactors.SearchUseCaseSubscriber
import kotlin.collections.ArrayList

class SearchCityViewModel(private val cityList: ArrayList<CityModel>) : ViewModel() {

    private var cityLiveData: MutableLiveData<ArrayList<CityModel>> = MutableLiveData()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchCityNavigation: SearchCityNavigation
    private var queryString: String? = null
    private val searchUseCase = SearchUseCase()

    fun getAdapter(): SearchAdapter {
        searchAdapter = SearchAdapter(searchCityNavigation)
        return searchAdapter
    }

    fun setNavigator(searchCityNavigation: SearchCityNavigation) {
        this.searchCityNavigation = searchCityNavigation
    }


    fun getCityMutableLiveData(): MutableLiveData<ArrayList<CityModel>> {
        return cityLiveData
    }

    fun searchCities() {
        searchUseCase.unsubscribe()
        searchUseCase.execute(cityList, queryString, SearchUseCaseSubscriber(cityLiveData))
    }

    fun setAdapterData(items: ArrayList<CityModel>) {

        searchAdapter.setCitiesList(items)
        searchAdapter.notifyDataSetChanged()
    }

    fun setQueryString(queryString: String) {
        this.queryString = queryString
    }

    fun getQueryString(): String? {
        return queryString
    }

    fun onDestroy() {
        searchUseCase.unsubscribe()
    }

}

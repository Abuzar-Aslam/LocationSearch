package com.abuzar.locationsearch.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abuzar.locationsearch.data.CityModel
import com.abuzar.locationsearch.interactors.SearchUseCase
import com.abuzar.locationsearch.interactors.SearchUseCaseSubscriber
import kotlin.collections.ArrayList

class SearchCityViewModel(private val cityList: ArrayList<CityModel>, private val searchUseCase: SearchUseCase) : ViewModel() {

    private var cityLiveData: MutableLiveData<List<CityModel>> = MutableLiveData()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchCityNavigation: SearchCityNavigation
    private var queryString: String? = null

    fun getAdapter(): SearchAdapter {
        searchAdapter = SearchAdapter(searchCityNavigation)
        return searchAdapter
    }

    fun setNavigator(searchCityNavigation: SearchCityNavigation) {
        this.searchCityNavigation = searchCityNavigation
    }


    fun getCityMutableLiveData(): MutableLiveData<List<CityModel>> {
        return cityLiveData
    }

    fun searchCities(newText: String) {
        setQueryString(newText)
        searchUseCase.execute(cityList, newText, SearchUseCaseSubscriber(this))
    }

    fun setAdapterData(items: List<CityModel>) {

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

    fun setCitiesListResult(citiesSearchResult :List<CityModel>){
        cityLiveData.postValue(citiesSearchResult)
    }

}

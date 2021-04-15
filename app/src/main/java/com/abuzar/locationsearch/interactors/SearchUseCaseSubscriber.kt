package com.abuzar.locationsearch.interactors

import android.util.Log
import com.abuzar.locationsearch.data.CityModel
import com.abuzar.locationsearch.ui.search.SearchCityViewModel
import kotlin.collections.ArrayList


class SearchUseCaseSubscriber(private val searchCityViewModel: SearchCityViewModel) :
    BaseUseCaseSubscriber<ArrayList<CityModel>>() {


    private val filteredCityList = ArrayList<CityModel>()

    override fun onNext(cityModelArrayList: ArrayList<CityModel>) {
        filteredCityList.addAll(cityModelArrayList)
    }

    override fun onComplete() {
        searchCityViewModel.setCitiesListResult(getSortedList(filteredCityList))
    }

    override fun onError(e: Throwable) {
        super.onError(e)
        e.message?.let {
            Log.e("Abuzar",it)

        }
    }

    fun getSortedList(citiesList: ArrayList<CityModel>): List<CityModel> {

        return if(citiesList.isNullOrEmpty()){
            emptyList()
        }else {
            citiesList.sortedWith(
                compareBy(String.CASE_INSENSITIVE_ORDER,
                    { it.cityName })
            )
        }
    }


}
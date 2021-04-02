package com.abuzar.locationsearch.interactors

import androidx.lifecycle.MutableLiveData
import com.abuzar.locationsearch.data.CityModel
import kotlin.Comparator
import kotlin.collections.ArrayList


class SearchUseCaseSubscriber(private val searchCityViewModel: MutableLiveData<ArrayList<CityModel>>) :
    BaseUseCaseSubscriber<ArrayList<CityModel>>() {


    private val filteredCityList = ArrayList<CityModel>()

    override fun onNext(cityModelArrayList: ArrayList<CityModel>) {

        filteredCityList.addAll(cityModelArrayList)

    }

    override fun onComplete() {

        filteredCityList.sortWith(Comparator { s1, s2 ->
            s1.cityName.compareTo(
                s2.cityName,
                ignoreCase = true
            )
        })
        searchCityViewModel.postValue(filteredCityList)
    }


}
package com.abuzar.locationsearch.ui.search

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abuzar.locationsearch.data.CityModel
import com.abuzar.locationsearch.ui.SearchAdapter
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import kotlin.collections.ArrayList


class SearchCityViewModel(private val cityList: ArrayList<CityModel>) : ViewModel() {

    private val filteredCityList: ArrayList<CityModel> = ArrayList<CityModel>()
    private var cityLiveData: MutableLiveData<ArrayList<CityModel>> = MutableLiveData()
    private val searchAdapter: SearchAdapter = SearchAdapter()

    fun getAdapter(): SearchAdapter {
        return searchAdapter
    }


    fun getCityMutableLiveData(): MutableLiveData<ArrayList<CityModel>> {
        return cityLiveData
    }

    fun searchCities(searchPrefix: String) {

        filteredCityList.clear()
        // Get the max number of threads we could have
        val threadCount = Runtime.getRuntime().availableProcessors()
        val threadPoolExecutor: ExecutorService = Executors.newFixedThreadPool(threadCount)
        val scheduler = Schedulers.from(threadPoolExecutor)


        val groupIndex = AtomicInteger()

        Observable.fromIterable(cityList)
            .groupBy { k -> groupIndex.getAndIncrement() % threadCount }
            .flatMapSingle { group ->
                group.observeOn(scheduler).toList()
                    .map { sublist ->
                        //Log.d("Abuzar","Thread Count is : "+threadCount)
                        val dataModels: ArrayList<CityModel> = ArrayList()
                        for (data in sublist) {
                            if (data.cityName.startsWith(searchPrefix, true)) {
                                dataModels.add(data)
                                //Log.e("Abuzar",data.cityName)
                            }

                        }
                        dataModels
                    }
            }
            .doOnComplete {
                //Log.e("Abuzar", "ONCOMPLETE")
                //sortFilteredList()

                cityLiveData.postValue(filteredCityList)
            }
            .subscribe { result ->
              //  Log.e("Abuzar", result.toString())
                filteredCityList += result
            }

    }

    fun sortFilteredList() {
        Log.e("Abuzar", "Sorting= ${filteredCityList.size}")
    }

    fun getAllCities(): LiveData<List<CityModel>> {
        val mutableLiveData = MutableLiveData<List<CityModel>>()
        mutableLiveData.value = cityList
        return mutableLiveData
    }

    fun setAdapterData(items: ArrayList<CityModel>) {

        searchAdapter.setCitiesList(items)
        searchAdapter.notifyDataSetChanged()
    }
}

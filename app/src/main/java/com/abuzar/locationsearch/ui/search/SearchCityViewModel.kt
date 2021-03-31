package com.abuzar.locationsearch.ui.search

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.abuzar.locationsearch.data.CityModel
import com.abuzar.locationsearch.ui.SearchAdapter
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import kotlin.collections.ArrayList
import androidx.navigation.fragment.findNavController
import com.abuzar.locationsearch.R

class SearchCityViewModel(private val cityList: ArrayList<CityModel>) : ViewModel() {

    private val filteredCityList: ArrayList<CityModel> = ArrayList<CityModel>()
    private var cityLiveData: MutableLiveData<ArrayList<CityModel>> = MutableLiveData()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchCityNavigation: SearchCityNavigation

    fun getAdapter(): SearchAdapter {
        searchAdapter= SearchAdapter(searchCityNavigation)
        return searchAdapter
    }

    fun setNavigator(searchCityNavigation: SearchCityNavigation) {
        this.searchCityNavigation = searchCityNavigation
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
                filteredCityList.sortWith(Comparator { s1, s2 ->
                    s1.cityName.compareTo(
                        s2.cityName,
                        ignoreCase = true
                    )
                })
                cityLiveData.postValue(filteredCityList)
            }
            .subscribe { result ->
                //  Log.e("Abuzar", result.toString())
                filteredCityList += result
            }

    }

    fun setAdapterData(items: ArrayList<CityModel>) {

        searchAdapter.setCitiesList(items)
        searchAdapter.notifyDataSetChanged()
    }
}

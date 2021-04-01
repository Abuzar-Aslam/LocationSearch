package com.abuzar.locationsearch.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abuzar.locationsearch.data.CityModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import kotlin.collections.ArrayList

class SearchCityViewModel(private val cityList: ArrayList<CityModel>) : ViewModel() {

    private val filteredCityList: ArrayList<CityModel> = ArrayList<CityModel>()
    private var cityLiveData: MutableLiveData<ArrayList<CityModel>> = MutableLiveData()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var searchCityNavigation: SearchCityNavigation
    private var queryString: String?=null
    private val compositeDisposable = CompositeDisposable()

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

        filteredCityList.clear()
        // Get the max number of threads we could have
        val threadCount = Runtime.getRuntime().availableProcessors()
        val threadPoolExecutor: ExecutorService = Executors.newFixedThreadPool(threadCount)
        val scheduler = Schedulers.from(threadPoolExecutor)


        val groupIndex = AtomicInteger()

        val disposable =Observable.fromIterable(cityList)
            .groupBy { k -> groupIndex.getAndIncrement() % threadCount }
            .flatMapSingle { group ->
                group.observeOn(scheduler).toList()
                    .map { sublist ->
                        //Log.d("Abuzar","Thread Count is : "+threadCount)
                        val dataModels: ArrayList<CityModel> = ArrayList()
                        for (data in sublist) {
                            queryString?.let {
                                if (data.cityName.startsWith(it, true)) {
                                    dataModels.add(data)
                                    //Log.e("Abuzar",data.cityName)
                                }
                            }


                        }
                        dataModels
                    }
            }
            .doOnComplete {
                if(filteredCityList.size>1){
                    filteredCityList.sortWith(Comparator { s1, s2 ->
                        s1.cityName.compareTo(
                            s2.cityName,
                            ignoreCase = true
                        )
                    })
                }
                cityLiveData.postValue(filteredCityList)
            }
            .subscribe { result ->
                //  Log.e("Abuzar", result.toString())
                filteredCityList += result
            }

        compositeDisposable.clear()
        compositeDisposable.add(disposable)

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

}

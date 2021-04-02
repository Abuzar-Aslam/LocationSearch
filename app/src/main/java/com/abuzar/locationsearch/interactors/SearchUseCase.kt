package com.abuzar.locationsearch.interactors

import android.util.Log
import com.abuzar.locationsearch.data.CityModel
import com.google.android.gms.tasks.Tasks.call
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.concurrent.atomic.AtomicInteger

class SearchUseCase() : BaseUseCase<ArrayList<CityModel>>() {

    lateinit var citiesList: ArrayList<CityModel>
    var queryString: String?=null
    fun <O> execute(
        citiesList: ArrayList<CityModel>,
        queryString: String?,
        disposableObserver: O
    ) where O : Disposable, O : Observer<ArrayList<CityModel>> {
        this.citiesList = citiesList
        this.queryString = queryString
        super.execute(disposableObserver)
    }


    override fun buildUseCaseObservable(): Observable<ArrayList<CityModel>> {

        val groupIndex = AtomicInteger()

        val threadCount = Runtime.getRuntime().availableProcessors()
        return Observable.fromIterable(citiesList)
            .groupBy { _ -> groupIndex.getAndIncrement() % threadCount }
            .flatMapSingle { group ->
                group.toList()
                    .map { sublist ->
                        Log.e("Abuzar", "FIRST MAP")
                        val dataModels: ArrayList<CityModel> = ArrayList()
                        for (data in sublist) {
                            queryString?.let {
                                if (data.cityName.startsWith(it, true)) {
                                    dataModels.add(data)
                                }
                            }
                        }
                        dataModels
                    }
            }
    }
}
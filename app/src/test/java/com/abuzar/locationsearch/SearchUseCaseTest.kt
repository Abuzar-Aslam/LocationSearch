package com.abuzar.locationsearch

import com.abuzar.locationsearch.data.CityModel
import com.abuzar.locationsearch.interactors.SearchUseCase
import com.abuzar.locationsearch.utils.getMockCitiesList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.collections.ArrayList


@RunWith(MockitoJUnitRunner::class)
class SearchUseCaseTest {


    lateinit var searchUseCase: SearchUseCase

    @Rule
    @JvmField
    val schedulersOverrideRule = RxImmediateSchedulerRule()


    @Before
    fun setUp() {
        searchUseCase = SearchUseCase()
    }


    @Test
    fun testSuccessSearchResult() {

        val citiesList = ArrayList<CityModel>()
        searchUseCase.citiesList = getMockCitiesList()
        searchUseCase.queryString = "a"
        searchUseCase.buildUseCaseObservable()
            .observeOn(Schedulers.io())
            .doOnNext { result ->
                citiesList.addAll(result)
            }
            .doOnComplete {
                Assert.assertEquals(citiesList.size, 2)
            }.subscribeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }


    @Test
    fun testEmptySearchResult() {

        val citiesList = ArrayList<CityModel>()
        searchUseCase.citiesList = getMockCitiesList()
        searchUseCase.queryString = "abc"
        searchUseCase.buildUseCaseObservable()
            .observeOn(Schedulers.io())
            .doOnNext { result ->
                citiesList.addAll(result)
            }
            .doOnComplete {
                Assert.assertEquals(citiesList.size, 0)
            }.subscribeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    @Test
    fun testDigitSearchResult() {

        val citiesList = ArrayList<CityModel>()
        searchUseCase.citiesList = getMockCitiesList()
        searchUseCase.queryString = "1"
        searchUseCase.buildUseCaseObservable()
            .observeOn(Schedulers.io())
            .doOnNext { result ->
                citiesList.addAll(result)
            }
            .doOnComplete {
                Assert.assertEquals(citiesList.size, 0)
            }.subscribeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }


}
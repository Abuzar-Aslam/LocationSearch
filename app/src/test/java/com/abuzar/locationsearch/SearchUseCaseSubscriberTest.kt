package com.abuzar.locationsearch

import com.abuzar.locationsearch.interactors.SearchUseCaseSubscriber
import com.abuzar.locationsearch.ui.search.SearchCityViewModel
import com.abuzar.locationsearch.utils.getMockCitiesList
import com.abuzar.locationsearch.utils.getMockSortedCitiesList
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.internal.util.collections.Iterables
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SearchUseCaseSubscriberTest {


    @Mock
    lateinit var searchCityViewModel: SearchCityViewModel

    lateinit var subscriber: SearchUseCaseSubscriber


    @Before
    fun setUp(){
        subscriber= SearchUseCaseSubscriber(searchCityViewModel)


    }

    @Test
    fun onNext() {
        val citiesArrayList = getMockCitiesList()
        searchCityViewModel.setCitiesListResult(getMockCitiesList())
        subscriber.onNext(citiesArrayList)
        subscriber.onComplete()
    }


    @Test
    fun testSortedList(){
        assertEquals(subscriber.getSortedList(getMockCitiesList()), getMockSortedCitiesList())
    }

}
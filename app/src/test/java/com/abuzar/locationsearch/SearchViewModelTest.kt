package com.abuzar.locationsearch

import com.abuzar.locationsearch.data.CityModel
import com.abuzar.locationsearch.interactors.SearchUseCase
import com.abuzar.locationsearch.interactors.SearchUseCaseSubscriber
import com.abuzar.locationsearch.ui.search.SearchCityViewModel
import com.abuzar.locationsearch.utils.getMockCitiesList
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {


    lateinit var searchCityViewModel: SearchCityViewModel

    @Rule @JvmField
    val schedulersOverrideRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var searchCityUseCase: SearchUseCase

    lateinit var queryString:String


    @Before
    fun setUp() {
        searchCityViewModel = SearchCityViewModel(getMockCitiesList(), searchCityUseCase)
        queryString="a"
    }


    @Test
    fun testSearchCities() {


        searchCityViewModel.searchCities(queryString)
        verify(searchCityUseCase).execute(anyList<CityModel>() as ArrayList<CityModel>, eq(queryString), any(SearchUseCaseSubscriber::class.java))
    }


}
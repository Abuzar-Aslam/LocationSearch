package com.abuzar.locationsearch.ui.search

import com.abuzar.locationsearch.data.CityModel

interface SearchCityNavigation {

    fun launchMapFragment(cityModel: CityModel)

}
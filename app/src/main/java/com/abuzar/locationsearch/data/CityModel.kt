package com.abuzar.locationsearch.data

import com.google.gson.annotations.SerializedName


data class CityModel(@SerializedName("country") val country: String,
                     @SerializedName("name") val cityName: String,
                     @SerializedName("coord") val coordinates: Coordinates)


data class Coordinates(@SerializedName("lon") val longitude: Double,
                       @SerializedName("lat") val latitude: Double)
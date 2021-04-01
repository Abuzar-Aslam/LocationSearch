package com.abuzar.locationsearch.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityModel(
    @SerializedName("country") val country: String,
    @SerializedName("name") val cityName: String,
    @SerializedName("coord") val coordinates: Coordinates
) : Parcelable {

    fun formattedTitle(): String {
        return "$cityName, $country"
    }
}


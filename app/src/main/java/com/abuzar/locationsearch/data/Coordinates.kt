package com.abuzar.locationsearch.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coordinates(@SerializedName("lon") val longitude: Double,
                       @SerializedName("lat") val latitude: Double):Parcelable {


    fun formattedLatLng(): String {
        return "$latitude, $longitude"
    }

                       }
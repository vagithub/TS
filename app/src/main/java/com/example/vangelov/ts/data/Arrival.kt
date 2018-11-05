package com.example.vangelov.ts.data

import com.google.gson.annotations.SerializedName

data class Arrival(
    @SerializedName("time") var time: String,
    @SerializedName("has_air_conditioning") var hasAirConditioning: Boolean,
    @SerializedName("has_wifi") var hasWifi: Boolean,
    @SerializedName("is_wheelchair_accessible") var isWheelChairAccessible: Boolean
) {
}
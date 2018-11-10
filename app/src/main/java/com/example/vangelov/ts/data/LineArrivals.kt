package com.example.vangelov.ts.data

import com.google.gson.annotations.SerializedName

const val BUS = "bus"
const val TROLLEY = "trolley"
const val TRAM = "tram"

data class LineArrivals(
    @SerializedName("vehicle_type") var vehicleType: String,
    @SerializedName("arrivals") var arrivals: List<Arrival>,
    @SerializedName("direction") var direction: String,
    @SerializedName("name") var name: String
)
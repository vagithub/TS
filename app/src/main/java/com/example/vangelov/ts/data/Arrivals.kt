package com.example.vangelov.ts.data

import com.google.gson.annotations.SerializedName

data class Arrivals(
    @SerializedName("license") var license: String = "",
    @SerializedName("code") var stopNumber: String = "",
    @SerializedName("lines") var lines: List<LineArrivals> = emptyList(),
    @SerializedName("timestamp_calculated") var timeStamp: String = "",
    @SerializedName("name") var name: String = ""
)
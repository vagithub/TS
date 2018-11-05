package com.example.vangelov.ts.data

import com.google.gson.annotations.SerializedName

data class Line(
    @SerializedName("vehicle_type") var vehicleType : String,
    @SerializedName("arrivals") var arrivals: Array<Arrival>,
    @SerializedName("direction") var direction: String,
    @SerializedName("name") var name: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Line

        if (vehicleType != other.vehicleType) return false
        if (!arrivals.contentEquals(other.arrivals)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = vehicleType.hashCode()
        result = 31 * result + arrivals.contentHashCode()
        return result
    }
}
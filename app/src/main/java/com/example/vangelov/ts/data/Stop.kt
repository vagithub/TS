package com.example.vangelov.ts.data

import com.google.gson.annotations.SerializedName

data class Stop(
    @SerializedName("n") val name: String,
    @SerializedName("y") val y: Double,
    @SerializedName("x") val x: Double,
    @SerializedName("c") val number: String
) {
    override fun toString(): String {
        return "$number $name"
    }
}
package com.example.vangelov.ts.data

data class Line(
    var id: String,
    var name: String,
    var routes: List<Route>
) {
    override fun toString(): String {
        return name
    }
}
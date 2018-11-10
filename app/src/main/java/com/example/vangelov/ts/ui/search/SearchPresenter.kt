package com.example.vangelov.ts.ui.search

import com.example.vangelov.ts.data.Stop
import net.grandcentrix.thirtyinch.TiPresenter
import okhttp3.OkHttpClient

abstract class SearchPresenter<T: SearchView>(http : OkHttpClient) : TiPresenter<T>() {

    lateinit var stopNumber: String
    protected lateinit var lineNumber: String
    protected lateinit var type: String

    protected val STOPS_URL = "https://routes.sofiatraffic.bg/resources/stops-bg.json"
    protected val ROUTES_URL = "https://routes.sofiatraffic.bg/resources/routes.json"
    //    protected var ARRIVALS_BY_LINE_URL = "https://api-arrivals.sofiatraffic.bg/api/v1/arrivals/$stopNumber/"
//    protected val ARRIVALS_URL = "https://api-arrivals.sofiatraffic.bg/api/v1/arrivals/$stopNumber/?line=$lineNumber&type=$type"
    protected val ARRIVALS_URL = "https://api-arrivals.sofiatraffic.bg/api/v1/arrivals/"
    //    https://api-arrivals.sofiatraffic.bg/api/v1/arrivals/1905/ - po spirka

    lateinit var stopsMap: MutableMap<String, Stop>
    lateinit var stopsList: List<Stop>

}
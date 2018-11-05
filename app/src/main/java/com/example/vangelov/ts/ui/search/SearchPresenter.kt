package com.example.vangelov.ts.ui.search

import com.example.vangelov.ts.data.Stop
import io.reactivex.SingleObserver
import net.grandcentrix.thirtyinch.TiPresenter
import net.grandcentrix.thirtyinch.TiView
import okhttp3.OkHttpClient
import okhttp3.Response

abstract class SearchPresenter<T: SearchView>(http : OkHttpClient) : TiPresenter<T>() {

    val STOPS_URL = "https://routes.sofiatraffic.bg/resources/stops-bg.json"
    private val ROUTES_URL = "https://routes.sofiatraffic.bg/resources/routes.json"
    private val ARRIVALS_URL = "https://api-arrivals.sofiatraffic.bg/api/v1/arrivals/0599/?line=76&type=bus"
    //    https://api-arrivals.sofiatraffic.bg/api/v1/arrivals/1905/ - po spirka

    lateinit var stopsMap: MutableMap<String, Stop>
    lateinit var stopsList: List<Stop>

}
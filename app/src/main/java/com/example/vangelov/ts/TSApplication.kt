package com.example.vangelov.ts

import android.app.Application
import com.example.vangelov.ts.data.Stop
import com.example.vangelov.ts.ui.search.SearchView
import com.example.vangelov.ts.ui.search.bystop.SearchByStopPresenter
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleOnSubscribe
import io.reactivex.schedulers.Schedulers
import net.grandcentrix.thirtyinch.TiPresenter
import okhttp3.*
import java.util.*

class TSApplication : Application() {

    private val STOPS_URL = "https://routes.sofiatraffic.bg/resources/stops-bg.json"
    private val ROUTES_URL = "https://routes.sofiatraffic.bg/resources/routes.json"
    private val ARRIVALS_URL = "https://api-arrivals.sofiatraffic.bg/api/v1/arrivals/0599/?line=76&type=bus"
//    https://api-arrivals.sofiatraffic.bg/api/v1/arrivals/1905/ - po spirka

    internal val httpClient: OkHttpClient = OkHttpClient.Builder().connectionSpecs(
        Arrays.asList(
            ConnectionSpec.MODERN_TLS,
            ConnectionSpec.COMPATIBLE_TLS
        )
    ).build()
}
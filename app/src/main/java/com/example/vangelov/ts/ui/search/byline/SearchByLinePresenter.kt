package com.example.vangelov.ts.ui.search.byline

import android.view.View
import android.widget.AdapterView
import com.example.vangelov.ts.R
import com.example.vangelov.ts.data.*
import com.example.vangelov.ts.ui.search.SearchPresenter
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.util.*

class SearchByLinePresenter(var http: OkHttpClient) : SearchPresenter<SearchByLineView>(http) {

    internal lateinit var transportationModes: List<TransportationMode>
    internal lateinit var lines: List<Line>
    internal lateinit var selectedLine: Line

    internal lateinit var routesObserver: RoutesObserver

    override fun onAttachView(view: SearchByLineView) {
        super.onAttachView(view)
//        type= BUS
        routesObserver = RoutesObserver(view)
//        setType(, view)
        view.selectButton(R.id.busBtn)
//        loadRoutes(view)
//        loadStops(view)
    }

    private fun loadRoutes(view: SearchByLineView) {
        var request = Request.Builder().url(ROUTES_URL).build()
        Single.create<Response> { emitter ->
            var response: Response = http.newCall(request).execute()
            emitter.onSuccess(response)
        }.subscribeOn(Schedulers.newThread())
            .subscribe(routesObserver)
    }

    private fun loadStops(view: SearchByLineView) {
        var request = Request.Builder().url(STOPS_URL).build()
        Single.create<Response> { emitter ->
            var response: Response = http.newCall(request).execute()
            emitter.onSuccess(response)
        }.subscribeOn(Schedulers.newThread())
            .subscribe(StopsObserver(view))
    }

    private fun getDirections(): Array<String?> {
        return Array(2) { i ->
            stopsMap[selectedLine.routes[i].codes[0]]?.name + " - " + stopsMap[selectedLine.routes[i].codes.last()]?.name
        }
    }

    private fun getArrivals(view: SearchByLineView) {
        var request = Request.Builder().url("$ARRIVALS_URL$stopNumber/?line=$lineNumber&type=$type").build()
        Single.create<Response> { emitter ->
            var response: Response = http.newCall(request).execute()
            emitter.onSuccess(response)
        }.subscribeOn(Schedulers.newThread())
            .subscribe(ArrivalsObserver(view))
    }

    fun setType(type: Int, view: SearchByLineView) {
        when (type) {
            R.id.busBtn -> this.type = BUS
            R.id.trolleyBtn -> this.type = TROLLEY
            R.id.tramBtn -> this.type = TRAM
        }
        loadRoutes(view)
        loadStops(view)

    }

    inner class RoutesObserver(private var view: SearchByLineView) : SingleObserver<Response> {
        override fun onSuccess(response: Response) {
            view.showLoading()
            var jsonText = response.body()?.string() //?: throw NullPointerException("No connection to backend")
            var gson = Gson()
            transportationModes = gson.fromJson(jsonText, Array<TransportationMode>::class.java).toList()
            if (transportationModes.isEmpty()) {
                //TODO throw exception
            }
            //todo - do not use for each
            transportationModes.forEach { transportationMode ->
                if (transportationMode.type == type) {
                    lines = transportationMode.lines
                    view.setLinesAdapter()
                    view.setLineOnTouchListener()
                    view.setClickOnLineListener(AdapterView.OnItemClickListener { parent, _, position, id ->
                        //                        lineNumber = (parent?.adapter?.getItem(position) as Line).id
                        selectedLine = (parent?.adapter?.getItem(position) as Line)
                        lineNumber = selectedLine.name
                        view.setDirectionsAdapter(getDirections())
                        view.hideKeyboard()
                    })
                    view.setClickOnDirectionListener(object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                        override fun onItemSelected(parent: AdapterView<*>?, view1: View?, position: Int, id: Long) {
                            view.showLoading()
                            var stops: List<Stop?> = List(selectedLine.routes[position].codes.size) { i ->
                                stopsMap[selectedLine.routes[position].codes[i]]
                            }
                            view.setStopsAdapter(stops)
                            view.hideLoading()

                        }
                    })
                    view.setClickOnStopListener(object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                        override fun onItemSelected(parent: AdapterView<*>?, view1: View?, position: Int, id: Long) {
                            stopNumber = (parent?.adapter?.getItem(position) as Stop).number
                            getArrivals(view)
                        }
                    })
                }
            }
            view.hideLoading()

        }

        override fun onSubscribe(d: Disposable) {
        }

        override fun onError(e: Throwable) {
            //TODO
            e.printStackTrace()
        }

    }

    inner class StopsObserver(private var view: SearchByLineView) : SingleObserver<Response> {
        override fun onSuccess(response: Response) {
            var jsonText = response.body()?.string() //?: throw NullPointerException("No connection to backend")
            var gson = Gson()
            stopsList = gson.fromJson(jsonText, Array<Stop>::class.java).toList()
            if (stopsList.isEmpty()) {
                //TODO throw exception
            }
            stopsMap = HashMap()
            stopsList.forEach { stop ->
                stopsMap[stop.number] = stop
            }
        }

        override fun onSubscribe(d: Disposable) {
        }

        override fun onError(e: Throwable) {
            //TODO
            e.printStackTrace()
        }
    }

    inner class ArrivalsObserver(private var view: SearchByLineView) : SingleObserver<Response> {
        override fun onSuccess(response: Response) {
            view.showLoading()
            var jsonText = response.body()?.string() //?: throw NullPointerException("No connection to backend")
            var gson = Gson()
            var arrivals = gson.fromJson(jsonText, Arrivals::class.java)
            view?.setArrivalsAdapter(arrivals.lines)
            view.hideLoading()
        }

        override fun onSubscribe(d: Disposable) {
        }

        override fun onError(e: Throwable) {
        }
    }
}
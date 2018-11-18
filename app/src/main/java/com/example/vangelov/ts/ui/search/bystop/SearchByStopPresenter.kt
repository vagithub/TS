package com.example.vangelov.ts.ui.search.bystop

import android.widget.AdapterView
import com.example.vangelov.ts.data.Arrivals
import com.example.vangelov.ts.data.Stop
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


class SearchByStopPresenter(var http: OkHttpClient) : SearchPresenter<SearchByStopView>(http) {

    internal lateinit var arrivals: Arrivals
    override fun onAttachView(view: SearchByStopView) {
        super.onAttachView(view)
        loadStops(view)
    }

    private fun loadStops(view: SearchByStopView) {
        var request = Request.Builder().url(STOPS_URL).build()
        Single.create<Response> { emitter ->
            var response: Response = http.newCall(request).execute()
            emitter.onSuccess(response)
        }.subscribeOn(Schedulers.newThread())
            .subscribe(StopsObserver(view))
    }

    private fun getArrivals(view: SearchByStopView) {
        var request = Request.Builder().url("$ARRIVALS_URL$stopNumber/").build()
        Single.create<Response> { emitter ->
            var response: Response = http.newCall(request).execute()
            emitter.onSuccess(response)
        }.subscribeOn(Schedulers.newThread())
            .subscribe(ArrivalsObserver(view))
    }

    inner class StopsObserver(private var view: SearchByStopView) : SingleObserver<Response> {
        override fun onSuccess(response: Response) {
            view.showLoading()
            var jsonText = response.body()?.string() //?: throw NullPointerException("No connection to backend")
            var gson = Gson()
            stopsList = gson.fromJson(jsonText, Array<Stop>::class.java).toList()
            if (stopsList.isEmpty()) {
                //TODO throw exception
            }
            //todo remove stopsmap
            stopsMap = HashMap()
            stopsList.forEach { stop ->
                stopsMap[stop.number] = stop
            }
            view.hideLoading()
            view.setStopsAdapter()
            view.setClickOnStopListener(AdapterView.OnItemClickListener { parent, view1, position, id ->
                stopNumber = (parent?.adapter?.getItem(position) as Stop).number
                getArrivals(view)
                view.hideKeyboard()
            })
            view.setOnTouchListener()
        }

        override fun onSubscribe(d: Disposable) {
        }

        override fun onError(e: Throwable) {
            //TODO
            e.printStackTrace()
        }

    }

    inner class ArrivalsObserver(private var view: SearchByStopView) : SingleObserver<Response> {
        override fun onSuccess(response: Response) {
            view.showLoading()
            var jsonText = response.body()?.string() //?: throw NullPointerException("No connection to backend")
            var gson = Gson()
            arrivals = gson.fromJson(jsonText, Arrivals::class.java)
            view?.setArrivalsAdapter(arrivals.lines)
            view.hideLoading()
        }

        override fun onSubscribe(d: Disposable) {
        }

        override fun onError(e: Throwable) {
        }
    }

}

//TODO - use
/*gson.newJsonReader(StringReader(jsonText)).use { reader ->
    reader.beginArray()
    var i=0
    while(reader.hasNext()){
        reader.beginObject()
        reader.nextName()
        val name = reader.nextString()
        reader.nextName()
        val x = reader.nextDouble()
        reader.nextName()
        val y = reader.nextDouble()
        reader.nextName()
        val number = reader.nextString()
        println(Stop(name, y, x, number))
        println("TKAMSE: $i")
        i++
        reader.endObject()
    }}*/
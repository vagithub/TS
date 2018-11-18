package com.example.vangelov.ts.ui.search.byline

import android.widget.AdapterView
import com.example.vangelov.ts.data.LineArrivals
import com.example.vangelov.ts.data.Stop
import com.example.vangelov.ts.ui.search.SearchView
import net.grandcentrix.thirtyinch.callonmainthread.CallOnMainThread

interface SearchByLineView : SearchView {

    //todo introduce enum
    @CallOnMainThread
    fun selectTransportationMode(mode: Int)

    @CallOnMainThread
    fun setLinesAdapter()

    @CallOnMainThread
    fun setDirectionsAdapter(directions: Array<String?>)

    @CallOnMainThread
    fun setClickOnLineListener(clickListener: AdapterView.OnItemClickListener)

    @CallOnMainThread
    fun setClickOnDirectionListener(clickListener: AdapterView.OnItemSelectedListener)

    @CallOnMainThread
    fun setStopsAdapter(stops: List<Stop?>)

    @CallOnMainThread
    fun setLineOnTouchListener()

    @CallOnMainThread
    fun setArrivalsAdapter(arrivals: List<LineArrivals>)

    @CallOnMainThread
    fun setClickOnStopListener(clickListener: AdapterView.OnItemSelectedListener)

    @CallOnMainThread
    fun selectButton(id: Int)
}
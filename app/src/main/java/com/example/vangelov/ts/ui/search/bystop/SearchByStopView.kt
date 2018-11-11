package com.example.vangelov.ts.ui.search.bystop

import android.widget.AdapterView
import com.example.vangelov.ts.data.LineArrivals
import com.example.vangelov.ts.ui.search.SearchView
import net.grandcentrix.thirtyinch.callonmainthread.CallOnMainThread

interface SearchByStopView : SearchView {


    @CallOnMainThread
    fun setStopsAdapter()

    @CallOnMainThread
    fun setArrivalsAdapter(arrivals: List<LineArrivals>)

    @CallOnMainThread
    fun setClickOnStopListener(clickListener: AdapterView.OnItemClickListener)

    @CallOnMainThread
    fun setOnTouchListener()
}
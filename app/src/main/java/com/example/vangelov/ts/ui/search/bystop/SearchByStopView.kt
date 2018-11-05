package com.example.vangelov.ts.ui.search.bystop

import com.example.vangelov.ts.ui.search.SearchView
import net.grandcentrix.thirtyinch.callonmainthread.CallOnMainThread

interface SearchByStopView : SearchView {

    @CallOnMainThread
    fun setAdapter()

    @CallOnMainThread
    fun showLoading()

    @CallOnMainThread
    fun hideLoading()
}
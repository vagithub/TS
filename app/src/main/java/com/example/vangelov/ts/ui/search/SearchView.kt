package com.example.vangelov.ts.ui.search

import net.grandcentrix.thirtyinch.TiView
import net.grandcentrix.thirtyinch.callonmainthread.CallOnMainThread

interface SearchView : TiView {

    @CallOnMainThread
    fun showLoading()

    @CallOnMainThread
    fun hideLoading()

    @CallOnMainThread
    fun hideKeyboard()
}
package com.example.vangelov.ts.ui.search

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import net.grandcentrix.thirtyinch.TiFragment

abstract class SearchFragment<P: SearchPresenter<V>, V: SearchView> : TiFragment<P, V>(), SearchView {

    lateinit var progressBar: ProgressBar

    override fun hideLoading() {
        (activity as SearchActivity).blockUI = false
        progressBar.visibility = View.GONE
    }

    override fun showLoading() {
        //todo not blocking
        progressBar.visibility = View.VISIBLE
        (activity as SearchActivity).blockUI = true
    }


    override fun hideKeyboard() {
        val inputManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(activity?.currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

}
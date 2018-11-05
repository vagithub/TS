package com.example.vangelov.ts.ui.search
import com.example.vangelov.ts.ui.search.SearchView
import net.grandcentrix.thirtyinch.TiFragment

abstract class SearchFragment<P: SearchPresenter<V>, V: SearchView> : TiFragment<P, V>(), SearchView {

}
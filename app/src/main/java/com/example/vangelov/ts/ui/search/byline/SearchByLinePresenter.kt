package com.example.vangelov.ts.ui.search.byline

import com.example.vangelov.ts.ui.search.SearchPresenter
import okhttp3.OkHttpClient

class SearchByLinePresenter(var http: OkHttpClient) : SearchPresenter<SearchByLineView>(http) {
}
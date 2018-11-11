package com.example.vangelov.ts.ui.search.byline


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.vangelov.ts.R
import com.example.vangelov.ts.ui.search.SearchFragment

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchByLineFragment : SearchFragment<SearchByLinePresenter, SearchByLineView>(), SearchByLineView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_by_line, container, false)
    }


}

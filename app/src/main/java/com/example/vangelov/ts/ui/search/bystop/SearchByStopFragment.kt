package com.example.vangelov.ts.ui.search.bystop


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ProgressBar
import com.example.vangelov.ts.R
import com.example.vangelov.ts.TSApplication
import com.example.vangelov.ts.data.Stop
import com.example.vangelov.ts.ui.search.SearchActivity
import com.example.vangelov.ts.ui.search.SearchFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchByStopFragment : SearchFragment<SearchByStopPresenter, SearchByStopView>(), SearchByStopView {
    override fun hideLoading() {
        (activity as SearchActivity).blockUI = false
        progressBar.visibility = View.GONE
    }

    override fun showLoading() {
        //todo
        (activity as SearchActivity).blockUI = true
        progressBar.visibility = View.VISIBLE
    }


    private lateinit var searchField: AutoCompleteTextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: SearchByStopPresenter

    override fun providePresenter(): SearchByStopPresenter {
        presenter = SearchByStopPresenter((activity?.application as TSApplication).httpClient)
        return presenter
    }

    override fun setAdapter() {
        searchField.setAdapter(ArrayAdapter<Stop>(context, android.R.layout.simple_list_item_1, presenter.stopsList))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_search_by_stop, container, false) as ViewGroup
        searchField = view.findViewById(R.id.stops_search_field)
        recyclerView = view.findViewById(R.id.arrivals_results_list)
        progressBar = view.findViewById(R.id.progressBar)
        return view
    }

}

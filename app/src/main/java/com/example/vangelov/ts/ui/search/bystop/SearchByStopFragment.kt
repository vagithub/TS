package com.example.vangelov.ts.ui.search.bystop


import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ProgressBar
import com.example.vangelov.ts.R
import com.example.vangelov.ts.TSApplication
import com.example.vangelov.ts.data.LineArrivals
import com.example.vangelov.ts.data.Stop
import com.example.vangelov.ts.ui.search.SearchActivity
import com.example.vangelov.ts.ui.search.SearchFragment

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
        progressBar.visibility = View.VISIBLE
        (activity as SearchActivity).blockUI = true
    }


    private lateinit var searchField: AutoCompleteTextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: SearchByStopPresenter

    override fun providePresenter(): SearchByStopPresenter {
        presenter = SearchByStopPresenter((activity?.application as TSApplication).httpClient)
        return presenter
    }

    override fun setStopsAdapter() {
        searchField.setAdapter(ArrayAdapter<Stop>(context, android.R.layout.simple_list_item_1, presenter.stopsList))
    }

    override fun setArrivalsAdapter(arrivals: List<LineArrivals>) {
        recyclerView.adapter = ArrivalsByStopAdapter(arrivals)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun setClickOnStopListener(clickListener: AdapterView.OnItemClickListener) {
        searchField.onItemClickListener = clickListener
    }

    override fun setOnTouchListener() {
        searchField.setOnTouchListener(ClearStopListener())
    }

    override fun hideKeyboard() {
        val inputManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(activity?.currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
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

    inner class ClearStopListener() : View.OnTouchListener {
        val drawable: Drawable = searchField.compoundDrawables[2]
        private val fuzz = 10

        override fun onTouch(v: View, event: MotionEvent?): Boolean {
            val bounds: Rect = drawable.bounds
            var x = event?.x
            var y = event?.y
            if (event?.action == MotionEvent.ACTION_DOWN) {
                if (x != null && y != null) {
                    if (x >= (bounds.left - fuzz) && x <= (v.right - v.paddingRight + fuzz)
                        && y >= (v.paddingTop - fuzz) && y <= (v.height - v.paddingBottom) + fuzz
                    ) {
                        searchField.setText("")
                        recyclerView.adapter = null
                        event.action = MotionEvent.ACTION_CANCEL
                        return false
                    }
                }
            }
            return false
        }
    }
}


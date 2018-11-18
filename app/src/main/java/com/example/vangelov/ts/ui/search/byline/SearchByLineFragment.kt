package com.example.vangelov.ts.ui.search.byline


import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.vangelov.ts.R
import com.example.vangelov.ts.TSApplication
import com.example.vangelov.ts.data.Line
import com.example.vangelov.ts.data.LineArrivals
import com.example.vangelov.ts.data.Stop
import com.example.vangelov.ts.ui.search.SearchFragment
import com.example.vangelov.ts.ui.search.bystop.ArrivalsByStopAdapter

/**
 * A simple [Fragment] subclass.
 *
 */
class SearchByLineFragment : SearchFragment<SearchByLinePresenter, SearchByLineView>(), SearchByLineView {

    private lateinit var busBtn: ImageButton
    private lateinit var trolleyBtn: ImageButton
    private lateinit var tramBtn: ImageButton
    private lateinit var selectLine: AutoCompleteTextView
    private lateinit var selectDirection: Spinner
    private lateinit var selectStop: Spinner
    private lateinit var scrollView: NestedScrollView
    private lateinit var arrivalsByLineList: RecyclerView
    private lateinit var presenter: SearchByLinePresenter

    private val buttonsIDs = arrayOf(R.id.busBtn, R.id.trolleyBtn, R.id.tramBtn)

    override fun providePresenter(): SearchByLinePresenter {
        presenter = SearchByLinePresenter((activity?.application as TSApplication).httpClient)
        return presenter
    }

    override fun selectTransportationMode(mode: Int) {
        when (mode) {
            R.id.busBtn -> return
            R.id.trolleyBtn -> return
            R.id.tramBtn -> return
        }
    }

    override fun setLinesAdapter() {
        selectLine.setAdapter(ArrayAdapter<Line>(context, android.R.layout.simple_list_item_1, presenter.lines))
    }

    override fun setClickOnLineListener(clickListener: AdapterView.OnItemClickListener) {
        selectLine.onItemClickListener = clickListener
    }

    override fun setDirectionsAdapter(directions: Array<String?>) {
        selectDirection.adapter = ArrayAdapter(context, R.layout.directions_item, directions)
    }

    override fun setClickOnDirectionListener(clickListener: AdapterView.OnItemSelectedListener) {
        selectDirection.onItemSelectedListener = clickListener
    }

    override fun setLineOnTouchListener() {
        selectLine.setOnTouchListener(ClearLineListener())
    }

    override fun setStopsAdapter(stops: List<Stop?>) {
        selectStop.adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, stops)
    }

    override fun setArrivalsAdapter(arrivals: List<LineArrivals>) {
        arrivalsByLineList.adapter = ArrivalsByStopAdapter(arrivals)
        arrivalsByLineList.layoutManager = LinearLayoutManager(context)
    }

    override fun setClickOnStopListener(clickListener: AdapterView.OnItemSelectedListener) {
        selectStop.onItemSelectedListener = clickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_search_by_line, container, false) as ViewGroup
        busBtn = view.findViewById(R.id.busBtn)
        trolleyBtn = view.findViewById(R.id.trolleyBtn)
        tramBtn = view.findViewById(R.id.tramBtn)
        selectLine = view.findViewById(R.id.selectLineDropdown)
        selectDirection = view.findViewById(R.id.directionSpinner)
        selectStop = view.findViewById(R.id.selectStopSpinner)
        scrollView = view.findViewById(R.id.nestedScrollView)
        arrivalsByLineList = view.findViewById(R.id.arrivalsByLineList)
        progressBar = view.findViewById(R.id.progressBar)

        busBtn.setOnClickListener { v -> selectButton(v.id) }
        trolleyBtn.setOnClickListener { v -> selectButton(v.id) }
        tramBtn.setOnClickListener { v -> selectButton(v.id) }
        return view
    }

    override fun selectButton(id: Int) {
        when (id) {
            R.id.busBtn -> {
                busBtn.setImageResource(R.drawable.ic_bus_inverted)
                trolleyBtn.setImageResource(R.drawable.ic_trolley)
                tramBtn.setImageResource(R.drawable.ic_tram)
                selectLine.setText("")
                selectDirection.adapter = null
                selectStop.adapter = null
                arrivalsByLineList.adapter = null
                selectLine.dismissDropDown()
                presenter.setType(id, this)
            }
            R.id.trolleyBtn -> {
                busBtn.setImageResource(R.drawable.ic_bus)
                trolleyBtn.setImageResource(R.drawable.ic_trolley_inverted)
                tramBtn.setImageResource(R.drawable.ic_tram)
                selectLine.setText("")
                selectDirection.adapter = null
                selectStop.adapter = null
                arrivalsByLineList.adapter = null
                selectLine.dismissDropDown()
                presenter.setType(id, this)
            }
            R.id.tramBtn -> {
                busBtn.setImageResource(R.drawable.ic_bus)
                trolleyBtn.setImageResource(R.drawable.ic_trolley)
                tramBtn.setImageResource(R.drawable.ic_tram_inverted)
                selectLine.setText("")
                selectDirection.adapter = null
                selectStop.adapter = null
                arrivalsByLineList.adapter = null
                selectLine.dismissDropDown()
                presenter.setType(id, this)
            }
        }
    }

    inner class ClearLineListener() : View.OnTouchListener {
        val drawable: Drawable = selectLine.compoundDrawables[2]
        private val fuzz = 2

        override fun onTouch(v: View, event: MotionEvent?): Boolean {
            val bounds: Rect = drawable.bounds
            var x = event?.x
            var y = event?.y
            if (event?.action == MotionEvent.ACTION_DOWN) {
                if (x != null && y != null) {
                    if (x >= (bounds.left - fuzz) && x <= (v.right - v.paddingRight + fuzz)
                        && y >= (v.paddingTop - fuzz) && y <= (v.height - v.paddingBottom) + fuzz
                    ) {
                        selectLine.setText("")
                        selectDirection.adapter = null
                        selectStop.adapter = null
                        arrivalsByLineList.adapter = null
                        event.action = MotionEvent.ACTION_CANCEL
                        return false
                    }
                }
            }
            return false
        }
    }
}

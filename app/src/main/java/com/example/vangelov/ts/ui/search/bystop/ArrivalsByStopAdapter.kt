package com.example.vangelov.ts.ui.search.bystop

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.vangelov.ts.R
import com.example.vangelov.ts.data.BUS
import com.example.vangelov.ts.data.LineArrivals
import com.example.vangelov.ts.data.TRAM
import com.example.vangelov.ts.data.TROLLEY

class ArrivalsByStopAdapter(private val lineArrivals: List<LineArrivals>) :
    RecyclerView.Adapter<ArrivalsByStopAdapter.ArrivalsItemHolder>() {

    class ArrivalsItemHolder(view: View) : RecyclerView.ViewHolder(view)

    private fun String.formatTimestamp(): String {
        return this.dropLast(3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArrivalsItemHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false) //maybe false as parameter
        return ArrivalsItemHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lineArrivals.size
    }

    override fun onBindViewHolder(holder: ArrivalsItemHolder, position: Int) {
        var times: String = String()
        val arrivals = lineArrivals[position].arrivals
        when (arrivals.size) {
            0 -> times = "Will not come today"
            in 1..3 -> arrivals.forEach {
                times += it.time.formatTimestamp() + " "
            }
            else -> for (i in 0..2) {
                times += arrivals[i].time.formatTimestamp() + " "
            }
        }
        holder.itemView.findViewById<ImageView>(R.id.arrivalImg).setImageResource(
            when (lineArrivals[position].vehicleType) {
                BUS -> R.drawable.ic_bus
                TROLLEY -> R.drawable.ic_trolley
                TRAM -> R.drawable.ic_tram
                else -> {
                    android.R.drawable.ic_menu_directions
                }
            }
        )
        holder.itemView.findViewById<TextView>(R.id.lineNumber).text = lineArrivals[position].name
        holder.itemView.findViewById<TextView>(R.id.arrivals).text = times
    }
}



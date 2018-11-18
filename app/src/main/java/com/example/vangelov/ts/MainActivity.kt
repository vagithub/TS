package com.example.vangelov.ts

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.example.vangelov.ts.ui.search.SearchActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.searchByStopBtn).setOnClickListener { clickSearchByStop() }
        findViewById<Button>(R.id.SearchByLineBtn).setOnClickListener { clickSearchByLine() }
    }

    private fun clickSearchByStop() {
        val searchIntent : Intent = Intent(this, SearchActivity::class.java)
        println(searchIntent.extras)
        searchIntent.putExtra("fragment", 1)
        startActivity(searchIntent)
    }

    private fun clickSearchByLine() {
        val searchIntent: Intent = Intent(this, SearchActivity::class.java)
        println(searchIntent.extras)
        searchIntent.putExtra("fragment", 2)
        startActivity(searchIntent)
    }
}

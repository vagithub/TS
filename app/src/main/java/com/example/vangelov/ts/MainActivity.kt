package com.example.vangelov.ts

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.vangelov.ts.ui.search.SearchActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button18).setOnClickListener {  clickSearchByStop() }
    }

    public fun clickSearchByStop() {
        val searchIntent : Intent = Intent(this, SearchActivity::class.java)
        startActivity(searchIntent)
    }
}

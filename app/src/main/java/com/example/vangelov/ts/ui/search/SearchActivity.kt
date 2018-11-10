package com.example.vangelov.ts.ui.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import com.example.vangelov.ts.R
import com.example.vangelov.ts.ui.search.bystop.SearchByStopFragment

class SearchActivity : AppCompatActivity() {
    internal var blockUI: Boolean = false

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (blockUI) {
            return true
        }
        return super.onTouchEvent(event)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val fragmentManager = supportFragmentManager
        var fragment = SearchByStopFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.searchFragmentContainer, fragment).commit()
    }
}

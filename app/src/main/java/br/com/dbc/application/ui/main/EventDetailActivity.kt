package br.com.dbc.application.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.dbc.application.R

class EventDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.event_detail_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.navHostFragmentDetail, EventDetailFragment.newInstance())
                .commitNow()
        }
    }
}
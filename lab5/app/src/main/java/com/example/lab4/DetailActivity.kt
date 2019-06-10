package com.example.lab4

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lab4.fragments.detail.DetailFragment.Companion.DAYWEATHER_ITEM
import com.example.lab4.dummy.WeatherContent
import com.example.lab4.fragments.detail.DetailFragment

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG,"onCreate: activity been created")
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish()
            return
        }
        setContentView(R.layout.activity_detail)

        val item = intent.getParcelableExtra<WeatherContent.DayWeatherItem>(DAYWEATHER_ITEM)
        val detailFragment = DetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(DAYWEATHER_ITEM, item)
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.detailFragment, detailFragment)
            .commit()

    }
    private val TAG = "DetailActivity"
}

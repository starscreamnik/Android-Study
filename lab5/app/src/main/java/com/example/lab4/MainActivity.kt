package com.example.lab4

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lab4.fragments.detail.DetailFragment.Companion.DAYWEATHER_ITEM
import com.example.lab4.dummy.WeatherContent
import com.example.lab4.fragments.detail.DetailFragment
import com.example.lab4.fragments.item.ItemFragment
import com.example.lab4.helpers.DataHelper

class MainActivity : AppCompatActivity(), ItemFragment.OnListFragmentInteractionListener, ItemFragment.OnRefreshListener {

    private val TAG: String = "MainActivity"
    private lateinit var data : DataHelper

    val fragment = ItemFragment()

    override fun onListFragmentInteraction(item: WeatherContent.DayWeatherItem) {

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val detailFragment = DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DAYWEATHER_ITEM, item)
                }

            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.details, detailFragment)
                .commit()
        } else {
            Log.d("kek_tag", item.toString())
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DAYWEATHER_ITEM, item)
            startActivity(intent)
        }
    }

    override fun onRefresh() {
        data.getData(fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        data = DataHelper(applicationContext)
        supportFragmentManager.beginTransaction()
            .replace(R.id.listFragment, fragment)
            .commit()
    }


    override fun onResume() {
        super.onResume()
        fragment.swipeContainer.isRefreshing = true
        data.getWeatherInfo(fragment)
    }
}


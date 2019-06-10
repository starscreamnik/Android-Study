package com.example.lab4

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lab4.helpers.DataHelper

class MainActivity : AppCompatActivity(), ItemFragment.OnListFragmentInteractionListener, ItemFragment.OnRefreshListener {

    private val TAG: String = "MainActivity"
    private val KEY: String = ""
    private lateinit var data : DataHelper

    val fragment = ItemFragment()

    override fun onListFragmentInteraction(position: Int) {
        Log.d(TAG, position.toString())

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(TAG, "landscape")
            val detailFragment = DetailFragment.newInstance(position)
            supportFragmentManager.beginTransaction()
                .replace(R.id.details, detailFragment)
                .commit()
        } else {
            val intent = Intent(applicationContext, DetailActivity::class.java)
            intent.putExtra(DetailFragment.ARG_POSITION, position)
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


package com.example.lab4

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG,"onCreate: activity been created")
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish()
            return
        }
        setContentView(R.layout.activity_detail)
        if(savedInstanceState == null){
            Log.d(TAG,"onCreate: SaveInstance = null")

            val position = intent.getIntExtra(DetailFragment.ARG_POSITION, 0)
            val detailFragment = DetailFragment.newInstance(position)

            Log.d(TAG, position.toString())
            supportFragmentManager.beginTransaction()
                .replace(R.id.detailFragment, detailFragment)
                .commit()
        }
    }
    private val TAG = "DetailActivity"
}

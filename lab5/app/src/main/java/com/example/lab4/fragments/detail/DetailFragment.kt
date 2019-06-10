package com.example.lab4.fragments.detail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.lab4.R
import com.example.lab4.dummy.WeatherContent
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlin.collections.ArrayList


class DetailFragment : Fragment() {
    private var items = ArrayList<String>()

    fun showDetailInfo() {
        items.clear()
        val item = arguments?.getParcelable<WeatherContent.DayWeatherItem>(DAYWEATHER_ITEM)
        Log.d("kek_tag", item.toString())
        if (item != null) {
            items.add("Дата: ${item.date}")
            items.add("Ночь: " + item.temp_map["0"] + "   Ощущается: " + item.feel_map["0"])
            items.add("Утро: " + item.temp_map["1"] + "   Ощущается: " + item.feel_map["1"])
            items.add("День: " + item.temp_map["2"] + "   Ощущается: " + item.feel_map["2"])
            items.add("Вечер: " + item.temp_map["3"] + "   Ощущается: " + item.feel_map["3"])
            items.add("Ветер: " + item.wind)
            items.add("Осадки: " + item.cloud)
            items.add("Влажность: " + item.humidity)
            items.add("Давление: " + item.pressure)

            val list = detailList
            list.adapter = ArrayAdapter<String>(this.context!!, android.R.layout.simple_list_item_1, items)
        }
    }

    override fun onStart() {
        super.onStart()
        showDetailInfo()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    companion object {

        const val DAYWEATHER_ITEM = "DAYWEATHER_ITEM"
    }
}

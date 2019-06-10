package com.example.lab4


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.lab4.dummy.WeatherContent
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlin.collections.ArrayList


class DetailFragment : Fragment() {
    private var items = ArrayList<String>()
    private val TAG = "DetailFragment"

    fun showDetailInfo() {
        items.clear()
        val position = arguments?.getInt(ARG_POSITION)
        if (position != null) {
            val item = WeatherContent.INFO.toList().sortedBy { it.first }[position]
            items.add("Дата: " + item.first)
            items.add("Ночь: " + item.second.temp_map["0"] + "   Ощущается: " + item.second.feel_map["0"])
            items.add("Утро: " + item.second.temp_map["1"] + "   Ощущается: " + item.second.feel_map["1"])
            items.add("День: " + item.second.temp_map["2"] + "   Ощущается: " + item.second.feel_map["2"])
            items.add("Вечер: " + item.second.temp_map["3"] + "   Ощущается: " + item.second.feel_map["3"])
            items.add("Ветер: " + item.second.wind)
            items.add("Осадки: " + item.second.cloud)
            items.add("Влажность: " + item.second.humidity)
            items.add("Давление: " + item.second.pressure)

            val list = detailList
            list.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, items)
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

        const val ARG_POSITION = "position of element in RecycleView"

        @JvmStatic
        fun newInstance(position: Int) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_POSITION, position)
                }
            }
    }
}

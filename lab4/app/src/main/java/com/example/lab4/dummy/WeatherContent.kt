package com.example.lab4.dummy

import java.util.ArrayList

object WeatherContent {

    var ITEMS: MutableList<WeatherItem> = ArrayList()
    var INFO: MutableMap<String, DayWeatherItem> = HashMap()

    fun processingData() {
        INFO.clear()

        for (item: WeatherItem in ITEMS)
            addInfoToDayItem(item)
    }

    private fun addInfoToDayItem(item: WeatherItem) {
        if (INFO[item.date] != null) {
            INFO[item.date]!!.temp_map[item.tod] = item.temp
            INFO[item.date]!!.feel_map[item.tod] = item.feel
            if (item.tod == "2") {
                INFO[item.date]!!.humidity = item.humidity
                INFO[item.date]!!.cloud = item.cloud
                INFO[item.date]!!.pressure = item.pressure
                INFO[item.date]!!.wind = item.wind
            }
        } else
            INFO[item.date] = createDayWeatherItem(item)
    }

    private fun createDayWeatherItem(item: WeatherItem): DayWeatherItem {
        val tmp = HashMap<String, String>(4)
        val fl = HashMap<String, String>(4)
        tmp[item.tod] = item.temp
        fl[item.tod] = item.feel
        return DayWeatherItem(tmp, fl, item.wind, item.cloud, item.humidity, item.pressure)
    }

    data class WeatherItem(
        val date: String,
        val feel: String,
        var tod: String,
        val temp: String,
        val wind: String,
        val cloud: String,
        val humidity: String,
        val pressure: String
    )

    data class DayWeatherItem(
        val temp_map: MutableMap<String, String>,
        val feel_map: MutableMap<String, String>,
        var wind: String,
        var cloud: String,
        var humidity: String,
        var pressure: String
    )
}

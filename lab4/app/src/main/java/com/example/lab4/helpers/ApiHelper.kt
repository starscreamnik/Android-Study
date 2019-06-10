package com.example.lab4.helpers

import com.example.lab4.dummy.WeatherContent
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiHelper {
    @GET("meteo.php?")
    fun getWeatherInfo(@Query("tid") tid: Int) : Call<List<WeatherContent.WeatherItem>>
    /*@GET("meteo.htm")
    fun getWeatherInfo() : Call<List<WeatherContent.WeatherItem>>*/


    companion object{
        val BASE_URL  = "http://icomms.ru/inf/"
        //val BASE_URL = "https://meteo.vagabun.now.sh/"
        fun create(): ApiHelper {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiHelper::class.java)
        }

    }
}
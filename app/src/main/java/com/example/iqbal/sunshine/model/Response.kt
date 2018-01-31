package com.example.iqbal.sunshine

import com.google.gson.annotations.SerializedName

/**
 * Created by ilham on 1/27/18.
 */

data class ForcastResponse(
        @field:SerializedName("cod")
        val cod: String? = "",

        @field:SerializedName("message")
        val message: String? = "",

        @field:SerializedName("city")
        val city: City? = City(),

        @field:SerializedName("list")
        val forcastList: List<Forcast>? = null
)

data class City(
        @field:SerializedName("id")
        val id: String? = "",

        @field:SerializedName("name")
        val name: String? = "",

        @field:SerializedName("country")
        val country: String? = ""
)

data class Forcast(
        @field:SerializedName("dt_txt")
        var dtTxt: String? = "",

        @field:SerializedName("weather")
        val weather: List<Weather>? = null,

        @field:SerializedName("main")
        val main: Temp? = null
)

data class Temp(
        @field:SerializedName("temp")
        val temp: String? = ""
)

data class Weather(
        @field:SerializedName("id")
        val id: String? = "",

        @field:SerializedName("main")
        val main: String? = "",

        @field:SerializedName("description")
        val description: String? = "",

        @field:SerializedName("icon")
        val icon: String? = ""
)
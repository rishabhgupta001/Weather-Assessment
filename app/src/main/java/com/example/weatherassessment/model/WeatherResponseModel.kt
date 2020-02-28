package com.example.weatherassessment.model

import com.example.weatherassessment.data.network.StatusCode

data class WeatherResponseModel (
    val base: String = "",
    val clouds: Clouds? = null,
    val cod: Int = -1,
    val coord: Coord? = null,
    val dt: Int = -1,
    val id: Int = -1,
    val main: Main? = null,
    val name: String = "",
    val sys: Sys? = null,
    val timezone: Int = -1,
    val visibility: Int = -1,
    val weather: List<Weather>? = null,
    val wind: Wind? = null,
    var msg: String = "",
    var statusCode: StatusCode = StatusCode.START
)
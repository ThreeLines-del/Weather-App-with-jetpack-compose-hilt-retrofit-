package com.linesapp.weatherapp.main.model

data class WeatherResponse(
    val current: Current,
    val location: Location,
    val condition: Condition
)
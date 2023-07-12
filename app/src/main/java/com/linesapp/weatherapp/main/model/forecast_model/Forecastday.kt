package com.linesapp.weatherapp.main.model.forecast_model

data class Forecastday(
    val astro: Astro,
    val date: String,
    val date_epoch: Double,
    val day: Day,
    val hour: List<Hour>
)
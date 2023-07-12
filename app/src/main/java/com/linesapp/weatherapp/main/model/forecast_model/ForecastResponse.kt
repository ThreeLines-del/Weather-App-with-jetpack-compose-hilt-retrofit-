package com.linesapp.weatherapp.main.model.forecast_model

data class ForecastResponse(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)
package com.linesapp.weatherapp.main.repository

import com.linesapp.weatherapp.main.api.RetrofitInstance

class WeatherRepository{
    suspend fun getCurrentWeather(location: String) =
        RetrofitInstance.api.getCurrentWeather(location)

    suspend fun searchWeather(searchQuery: String) =
        RetrofitInstance.api.searchWeather(searchQuery)

    suspend fun getForecast(location: String) =
        RetrofitInstance.api.getForecast(location)
}
package com.linesapp.weatherapp.main.model.search_model

data class ResponseSearchItem(
    val country: String,
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String,
    val url: String
)
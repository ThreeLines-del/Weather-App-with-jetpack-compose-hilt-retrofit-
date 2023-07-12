package com.linesapp.weatherapp.main.ui

import androidx.lifecycle.ViewModel
import com.linesapp.weatherapp.main.model.WeatherResponse
import com.linesapp.weatherapp.main.model.forecast_model.ForecastResponse
import com.linesapp.weatherapp.main.model.search_model.ResponseSearch
import com.linesapp.weatherapp.main.model.search_model.ResponseSearchItem
import com.linesapp.weatherapp.main.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel(){

    fun getCurrentWeather(city: String): Flow<Response<WeatherResponse>> = flow {
        val currentWeather = repository.getCurrentWeather(city)
        emit(currentWeather)
    }

    fun searchWeatherFlow(searchQuery: String): Flow<ArrayList<ResponseSearchItem>> = flow {
        val searchWeather = repository.searchWeather(searchQuery)
        val weatherItems = mapResponseToSearchNewsItems(searchWeather)
        emit(weatherItems)
    }

    private fun mapResponseToSearchNewsItems(response: Response<ResponseSearch>): ArrayList<ResponseSearchItem>{
        val weatherItems = arrayListOf<ResponseSearchItem>()
        for(item in response.body()!!){
            weatherItems.add(
                ResponseSearchItem(
                    country = item.country,
                    id = item.id,
                    lat = item.lat,
                    lon = item.lon,
                    name = item.name,
                    region = item.region,
                    url = item.url
                )
            )
        }
        return weatherItems
    }

    fun getForecast(location: String): Flow<Response<ForecastResponse>> = flow {
        val forecast = repository.getForecast(location)
        emit(forecast)
    }

}
package com.linesapp.weatherapp.main.di

import com.linesapp.weatherapp.main.api.RetrofitInstance
import com.linesapp.weatherapp.main.api.WeatherAPI
import com.linesapp.weatherapp.main.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Modules {

    @Provides
    @Singleton
    fun providesAPI(): WeatherAPI{
        return RetrofitInstance.api
    }

    @Provides
    @Singleton
    fun providesRepository(): WeatherRepository{
        return WeatherRepository()
    }
}
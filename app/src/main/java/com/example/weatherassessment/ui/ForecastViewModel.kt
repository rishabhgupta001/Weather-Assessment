package com.example.weatherassessment.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherassessment.data.repository.ForecastRepository
import com.example.weatherassessment.model.WeatherResponseModel

class ForecastViewModel(val repository: ForecastRepository) : ViewModel() {
    var currentWeatherLiveData = MutableLiveData<WeatherResponseModel>()

    fun getCurrentWeather(zipCode: String) {
        currentWeatherLiveData = repository.getCurrentWeather(zipCode)
    }

}
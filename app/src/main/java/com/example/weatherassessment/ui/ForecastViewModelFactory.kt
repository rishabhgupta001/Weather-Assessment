package com.example.weatherassessment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherassessment.data.repository.ForecastRepository

@Suppress("UNCHECKED_CAST")
class ForecastViewModelFactory(private val repositry: ForecastRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ForecastViewModel(repositry) as T
    }
}


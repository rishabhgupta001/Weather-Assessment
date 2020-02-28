package com.example.weatherassessment.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherassessment.data.network.StatusCode
import com.example.weatherassessment.data.repository.ForecastRepository
import com.example.weatherassessment.model.WeatherResponseModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class ForecastViewModel(val repository: ForecastRepository) : ViewModel() {
    val TAG = ForecastViewModel::class.java.simpleName
    var currentWeather: MutableLiveData<WeatherResponseModel> = MutableLiveData()


    @SuppressLint("CheckResult")
    fun getCurrentWeather(zip: String) {
        val responseModel = WeatherResponseModel()

        repository.getCurrentWeather(zip)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                responseModel.statusCode = StatusCode.START
                currentWeather.value = responseModel
            }
            .observeOn(AndroidSchedulers.mainThread())
            //below code is working on Main thread
            .subscribe(
                { success ->
                    responseModel.statusCode = StatusCode.SUCCESS
                    currentWeather.value = success
                }, { error ->
                    Log.d(TAG, "api error ${error.message}")
                    responseModel.msg = error.localizedMessage!!
                    responseModel.statusCode = StatusCode.ERROR
                    currentWeather.value = responseModel
                })
    }

}

/*
    var weatherData: MutableLiveData<WeatherDataModel> = MutableLiveData()
    var weeklyWeatherData: MutableLiveData<List<WeatherDataModel>> = MutableLiveData()
    var networkState: MutableLiveData<NetworkState> = MutableLiveData()


    init {
        getCurrentWeather()
        getWeeklyWeather()
    }

    private fun getWeeklyWeather() {
        networkState.value = NetworkState.START
        weatherRepository.getWeeklyWeather("110089,IN")
            .subscribe(object : Observer<Any> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(value: Any) {
                    networkState.postValue(NetworkState.SUCCESS)
                    Log.e("data", "done")
                }

                override fun onError(e: Throwable) {
                    networkState.postValue(NetworkState.ERROR)
                }

            })

    }



    private fun getCurrentWeather() {
        networkState.value = NetworkState.START
        weatherRepository.getWeather("110089,IN")
            .subscribe(object : Observer<WeatherDataModel> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(value: WeatherDataModel) {
                    networkState.postValue(NetworkState.SUCCESS)
                    weatherData.postValue(value)
                    Log.e("data", value.main.toString())
                }

                override fun onError(e: Throwable) {
                    networkState.postValue(NetworkState.ERROR)
                }

            })
    }*/